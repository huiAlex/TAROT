package edu.ncsu.csc.itrust.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.beans.PersonnelBean;
import edu.ncsu.csc.itrust.beans.TransactionBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.dao.mysql.TransactionDAO;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.iTrustException;

/**
 * Handles retrieving the log of record accesses for a given user Used by viewAccessLog.jsp
 * 
 * @author laurenhayward
 * 
 */
public class ViewMyAccessLogAction {
	private TransactionDAO transDAO;
	private PatientDAO patientDAO;
	private long loggedInMID;

	/**
	 * Set up
	 * 
	 * @param factory The DAOFactory used to create the DAOs used in this action.
	 * @param loggedInMID The MID of the person retrieving the logs.
	 */
	public ViewMyAccessLogAction(DAOFactory factory, long loggedInMID) {
		this.loggedInMID = loggedInMID;
		this.transDAO = factory.getTransactionDAO();
		this.patientDAO = factory.getPatientDAO();
	}

	/**
	 * Returns a list of TransactionBeans between the two dates passed as params
	 * 
	 * @param lowerDate
	 *            the first date
	 * @param upperDate
	 *            the second date
	 * @return list of TransactionBeans
	 * @throws DBException
	 * @throws FormValidationException
	 */
	public List<TransactionBean> getAccesses(String lowerDate, String upperDate, String logMID, boolean getByRole) throws iTrustException, DBException,
			FormValidationException {
		List<TransactionBean> accesses; //stores the log entries
		List<PersonnelBean> dlhcps; 
		
		//get the medical dependents for a signed in user. If the selected user is not the
		//signed in user or one of the dependents, then the user doesn't have access to the log
		List<PatientBean> patientRelatives = getRepresented(loggedInMID); 
		
		long mid = loggedInMID;
		try {
			mid = Long.parseLong(logMID);
		} catch (Exception e) {		
		}
		
		dlhcps = patientDAO.getDeclaredHCPs(mid);
		
		boolean midInScope = false;
		for (PatientBean pb : patientRelatives) {
			if (pb.getMID() == mid) 
				midInScope = true;
		}
		if (mid != loggedInMID && !midInScope) { //the selected user in the form is out of scope and can't be shown to the user
			throw new FormValidationException("Log to View.");
		}
		
		//user has either 0 or 1 DLHCP's. Get one if exists so it can be filtered from results
		long dlhcpID = -1;
		if(!dlhcps.isEmpty())
			dlhcpID = dlhcps.get(0).getMID();
		
		if (lowerDate == null || upperDate == null)
			return transDAO.getAllRecordAccesses(mid, dlhcpID, getByRole);
		
		try {
			Date lower = new SimpleDateFormat("MM/dd/yyyy").parse(lowerDate);
			Date upper = new SimpleDateFormat("MM/dd/yyyy").parse(upperDate);

			if (lower.after(upper))
				throw new FormValidationException("Start date must be before end date!");
			accesses = transDAO.getRecordAccesses(mid, dlhcpID, lower, upper, getByRole);
		} catch (ParseException e) {
			throw new FormValidationException("Enter dates in MM/dd/yyyy");
		} 
		return accesses;
	}

	/**
	 * Returns the date of the first Transaction in the list passed as a param if the list is not empty
	 * otherwise, returns today's date
	 * 
	 * @param accesses A java.util.List of TransactionBeans for the accesses.
	 * @return A String representing the date of the first transaction.
	 */
	public String getDefaultStart(List<TransactionBean> accesses) {
		String startDate = "";
		if (accesses.size() > 0) {
			startDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date(accesses.get(accesses.size() - 1)
					.getTimeLogged().getTime()));
		} else {
			startDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		}
		return startDate;
	}

	/**
	 * Returns the date of the last Transaction in the list passed as a param if the list is not empty
	 * otherwise, returns today's date
	 * 
	 * @param accesses A java.util.List of TransactionBeans storing the access. 
	 * @return A String representation of the date of the last transaction.
	 */
	public String getDefaultEnd(List<TransactionBean> accesses) {
		String endDate = "";
		if (accesses.size() > 0) {
			endDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date(accesses.get(0).getTimeLogged()
					.getTime()));
		} else {
			endDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		}
		return endDate;
	}
	
	/**
	 * Return a list of patients that pid represents
	 * 
	 * @param pid The id of the personnel we are looking up representees for.
	 * @return a list of PatientBeans
	 * @throws iTrustException
	 */
	public List<PatientBean> getRepresented(long pid) throws iTrustException {
		return patientDAO.getRepresented(pid);
	}
}