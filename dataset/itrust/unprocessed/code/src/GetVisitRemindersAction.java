package edu.ncsu.csc.itrust.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import edu.ncsu.csc.itrust.beans.forms.VisitReminderReturnForm;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.VisitRemindersDAO;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.beans.ProcedureBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.beans.VisitFlag;
import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.enums.Gender;

/**
 * Gets the VisitReminders for a given patient Used by visitReminders.jsp
 * 
 * @author laurenhayward
 * 
 */
public class GetVisitRemindersAction {

	/**
	 * Reminder Type enumeration.
	 */
	public static enum ReminderType {
		DIAGNOSED_CARE_NEEDERS("Diagnosed Care Needers"),
		FLU_SHOT_NEEDERS("Flu Shot Needers"),
		IMMUNIZATION_NEEDERS("Immunization Needers");

		private String typeName;

		private ReminderType(String typeName) {
			this.typeName = typeName;
		}

		private static final HashMap<String, ReminderType> map = new HashMap<String, ReminderType>();
		static {
			for (ReminderType rt : ReminderType.values()) {
				map.put(rt.getTypeName(), rt);
			}
		}

		/**
		 * Gets the ReminderType for the name passed as a param
		 * 
		 * @param name
		 * @return the ReminderType associated with the name
		 */
		public static ReminderType getReminderType(String name) {
			return map.get(name);
		}

		/**
		 * Returns the type name as a string
		 * 
		 * @return
		 */
		public String getTypeName() {
			return typeName;
		}
	}

	/**
	 * 
	 * Begin GetVisitRemindersAction code
	 * 
	 */
	private VisitRemindersDAO visitReminderDAO;
	private PatientDAO patientDAO;
	private long loggedInMID;

	/**
	 * Set up defaults
	 * 
	 * @param factory The DAOFactory used to create the DAOs used in this action.
	 * @param loggedInMID MID of the person who is logged in
	 * @throws iTrustException
	 */
	public GetVisitRemindersAction(DAOFactory factory, long loggedInMID) throws iTrustException {
		this.loggedInMID = loggedInMID;
		visitReminderDAO = factory.getVisitRemindersDAO();
		patientDAO = factory.getPatientDAO();
	}

	/**
	 * Returns a list of VisitReminderReturnForms for the type passed in as a param
	 * 
	 * @param type
	 *            the ReminderType
	 * @return the list of VisitReminderReturnForms
	 * @throws iTrustException
	 * @throws FormValidationException
	 */
	public List<VisitReminderReturnForm> getVisitReminders(ReminderType type) throws iTrustException, FormValidationException {
		
		if (null == type)
			throw new iTrustException("Reminder Type DNE");
		
		switch (type) {
			case DIAGNOSED_CARE_NEEDERS:
				return visitReminderDAO.getDiagnosedVisitNeeders(loggedInMID);
				//return stripDupes(visitReminderDAO.getDiagnosedVisitNeeders(loggedInMID));

			case FLU_SHOT_NEEDERS:
				return visitReminderDAO.getFluShotDelinquents(loggedInMID);
				
			case IMMUNIZATION_NEEDERS:
				return getImmunizationNeeders(loggedInMID);
				
			default:
				throw new iTrustException("Reminder Type DNE");
		}
	}
	
	/**
	 * Gets a list of anyone who need immunizations
	 * 
	 * @param mid the HCP whose patients are being checked
	 * @return a list of all the people who need immunizations--done in a visit reminder
	 * @throws iTrustException
	 */

	private List<VisitReminderReturnForm> getImmunizationNeeders(long mid) throws iTrustException {
		
		List<VisitReminderReturnForm> formList;
		List<VisitReminderReturnForm> needList = new ArrayList<VisitReminderReturnForm>();
		String reason = "";
		// Get list of patients that designate this HCP
		formList = visitReminderDAO.getPatients(mid);
		
		for (VisitReminderReturnForm r : formList) {
			reason = checkImmunizations(r.getPatientID());
			if (0 < reason.length()) {
				needList.add(r);
				r.addVisitFlag(new VisitFlag(VisitFlag.IMMUNIZATION, reason));
			}
		}	
		
		return needList;
	}
	
	

	/**
	 * Checks a patient to see what immunizations they need
	 * 
	 * @param pid patient to be checked
	 * @return patient list of those lacking immunizations according to the schedule
	 */
	private String checkImmunizations(long pid) throws iTrustException {
		
		String reason = "";
		List<ProcedureBean> procs = patientDAO.getProcedures(pid);
		PatientBean patient = patientDAO.getPatient(pid);
		//long patientAge = patient.getAgeInWeeks();
		Date patientDOB = patient.getDateOfBirth();
		Gender gen = patient.getGender();
		
		int hepB = 0;
		long hepBTime = 0;

		int rota = 0;
		long rotaTime = 0;

		int diptet = 0;
		long deptetTime = 0;
		
		int haemoflu = 0;
		long haemofluTime = 0;
		long haemofluTimeFirst = 0;
		
		int pneumo = 0;
		long pneumoTime = 0;
		long pneumofluTimeFirst = 0;
		
		int polio = 0;
		long polioTime = 0;
		
		int measles = 0;
		long measlesTime = 0;
		
		int varicella = 0;
		long varicellaTime = 0;
		
		int hepA = 0;
		long hepATime = 0;
		
		int hpv = 0;
		long hpvTime = 0;
		
		for (ProcedureBean proc: procs) {
		
			String cpt = proc.getCPTCode();
			
			// Hep B (90371)
			if (cpt.equals("90371")) {
				hepB++;
				hepBTime = proc.getDate().getTime();
			}
				
			// Rotavirus (90681)
			else if (cpt.equals("90681")) {
				rota++;
				rotaTime = proc.getDate().getTime();
			}
			
			// Diptheria, Tetanus, Pertussis (90696)
			else if (cpt.equals("90696")) {
				diptet++;
				deptetTime = proc.getDate().getTime();
			}
			
			// Haemophilus influenza (90645)
			else if (cpt.equals("90645")) {
				if (0 == haemoflu)
					haemofluTimeFirst = proc.getDate().getTime();
				
				haemoflu++;
				haemofluTime = proc.getDate().getTime();
				
			}
			
			// Pneumococcal (90669)
			else if (cpt.equals("90669")) {
				if (0 == pneumo)
					pneumofluTimeFirst = proc.getDate().getTime();
				pneumo++;
				pneumoTime = proc.getDate().getTime();
			}
			
			// Poliovirus (90712)
			else if (cpt.equals("90712")) {
				polio++;
				polioTime = proc.getDate().getTime();
			}
			
			// Measles, Mumps, Rubella (90707)
			else if (cpt.equals("90707")) {
				measles++;
				measlesTime = proc.getDate().getTime();
			}
			
			// Varicella (90396)
			else if (cpt.equals("90396")) {
				varicella++;
				varicellaTime = proc.getDate().getTime();
			}
			
			// Hep A (90633)
			else if (cpt.equals("90633")) {
				hepA++;
				hepATime = proc.getDate().getTime();
			}
			
			// Human Papillomaavirus (90649)
			else if (cpt.equals("90649")) {
				hpv++;
				hpvTime = proc.getDate().getTime();
			}
		}
		
		if (3 > hepB) {
			reason += testHepB(hepB, patientDOB, hepBTime);
		}
		
		if (3 > rota) {
			reason += testRotaVirus(rota, patientDOB, rotaTime);
		}
		
		if (6 > diptet) {
			reason += testDipTet(diptet, patientDOB, deptetTime);
		}
		
		if (3 > haemoflu) {
			reason += testHaemoFlu(haemoflu, patientDOB, haemofluTime, haemofluTimeFirst);
		}
		
		if (4 > pneumo) {
			reason += testPneumo(pneumo, patientDOB, pneumoTime, pneumofluTimeFirst);
		}
		
		if (3 > polio) {
			reason += testPolio(polio, patientDOB, polioTime);
		}
		
		if (2 > measles) {
			reason += testMeasles(measles, patientDOB, measlesTime);
		}
		
		if (2 > varicella) {
			reason += testVaricella(varicella, patientDOB, varicellaTime);
		}
		
		if (2 > hepA) {
			reason += testHepA(hepA, patientDOB, hepATime);
		}
		
		if (3 > hpv && gen.getName().equals("Female")) {
			reason += testHPV(hpv, patientDOB, hpvTime);
		}
	
		if(reason.length() > 2)
			return reason.substring(0, reason.length() - 2);
		
		return reason;
	}
	
	
	/**
	 * Checks to see if a patient needs the HPV immunization
	 * 
	 * @param count			how many HPV immunizations she has already had
	 * @param patientAge	how old the patient is, in weeks
	 * @param time			date of the last procedure
	 * @return the			reason the immunization should be given, including required immunization age
	 */
	public static String testHPV(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		new Date();
		
		
		
		
		
		if (0 == count) {
			if (olderThan(patientDOB, 9, 0, 0))
				reason += "90649 Human Papillomavirus (9 years), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 9, 2, 0) && 8 <= weeks)
				reason += "90649 Human Papillomavirus (9 years, 2 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 9, 6, 0) && 16 <= weeks)
				reason += "90649 Human Papillomavirus (9 years, 6 months), ";
		}
		
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Hepatits A immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testHepA(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 1, 0, 0))
				reason += "90633 Hepatits A (12 months), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 1, 6, 0) && 26 <= weeks)
				reason += "90633 Hepatits A (18 months), ";
		}
		
		return reason;	
	}
	
	/**
	 * Checks to see if a patient needs the Varicella immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testVaricella(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 1, 0, 0))
				reason += "90396 Varicella (12 months), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 4, 0, 0) && 12 <= weeks)
				reason += "90396 Varicella (4 years), ";
		}
		
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Measles, Mumps, and Rubekka immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testMeasles(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 1, 0, 0))
				reason += "90707 Measles, Mumps, Rubekka (12 months), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 4, 0, 0) && 12 <= weeks)
				reason += "90707 Measles, Mumps, Rubekka (4 years), ";
		}
		
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Polio immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testPolio(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 6))
				reason += "90712 Poliovirus (6 weeks), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 4, 0) && 4 <= weeks)
				reason += "90712 Poliovirus (4 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0))
				reason += "90712 Poliovirus (6 months), ";				
		}
		
		return reason;	
	}
	
	/**
	 * Checks to see if a patient needs the Pneumococcal immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testPneumo(int count, Date patientDOB, long time, long firstDoseTime) {
		String reason = "";
		Date firstDose = new Date(firstDoseTime);
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		//long ageFirst = patientAge - (firstDoseTime / (1000 * 60 * 60 * 24 * 7)); 
		
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 6))
				reason += "90669 Pneumococcal (6 weeks), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 4, 0) && !(firstDoseAfter(patientDOB, firstDose, 1,0,0)) && 4 <= weeks)
				reason += "90669 Pneumococcal (4 months), ";
			else if (olderThan(patientDOB, 0, 4, 0) && (firstDoseAfter(patientDOB, firstDose, 1,0,0)) && !(firstDoseAfter(patientDOB, firstDose, 1,2,0)) && 8 <= weeks)
				reason += "90669 Pneumococcal (4 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0) && 4 <= weeks && !(firstDoseAfter(patientDOB, firstDose, 1,0,0)))
				reason += "90669 Pneumococcal (6 months), ";				
		}
		else if (3 == count) {
			if (olderThan(patientDOB, 1, 0, 0) && 8 <= weeks && !(firstDoseAfter(patientDOB, firstDose, 1,0,0)))
				reason += "90669 Pneumococcal (12 months), ";				
		}
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Haemophilus Infulenzae immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testHaemoFlu(int count, Date patientDOB, long time, long firstDoseTime) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		Date firstDose = new Date(firstDoseTime);
		//long ageFirst = patientAge - (firstDoseTime / (1000 * 60 * 60 * 24 * 7)); 
			
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 6))
				reason += "90645 Haemophilus influenzae (6 weeks), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 4, 0) && !(firstDoseAfter(patientDOB, firstDose, 1, 0, 0)) && 4 <= weeks)
				reason += "90645 Haemophilus influenzae (4 months), ";
			else if (olderThan(patientDOB, 0, 4, 0) && firstDoseAfter(patientDOB, firstDose, 1, 0, 0) && !(firstDoseAfter(patientDOB, firstDose, 1, 2, 0)) && 8 <= weeks)
				reason += "90645 Haemophilus influenzae (4 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0) && 4 <= weeks && !(firstDoseAfter(patientDOB, firstDose, 1, 0, 0)))
				reason += "90645 Haemophilus influenzae (6 months), ";
		}
		
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Diphtheria, Tetanus, Pertussis immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testDipTet(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 6))
				reason += "90696 Diphtheria, Tetanus, Pertussis (6 weeks), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 4, 0) && 4 <= weeks )
				reason += "90696 Diphtheria, Tetanus, Pertussis (4 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0) && 4 <= weeks)
				reason += "90696 Diphtheria, Tetanus, Pertussis (6 months), ";				
		}
		else if (3 == count) {
			if (olderThan(patientDOB, 0, 0, 15) && 26 <= weeks)
				reason += "90696 Diphtheria, Tetanus, Pertussis (15 weeks), ";
		}
		else if (4 == count) {
			if (olderThan(patientDOB, 4, 0, 0) && 26 <= weeks)
				reason += "90696 Diphtheria, Tetanus, Pertussis (4 years), ";
		}
		else if (5 == count) {
			if (olderThan(patientDOB, 11, 0, 0) && 260 <= weeks)
				reason += "90696 Diphtheria, Tetanus, Pertussis (11 years), ";				
		}
		
		return reason;
	}
	
	/**
	 * Checks to see if a patient needs the Rotavirus immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	
	public static String testRotaVirus(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 6))
				reason += "90681 Rotavirus (6 weeks), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 4, 0) && 4 <= weeks)
				reason += "90681 Rotavirus (4 months), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0) && 4 <= weeks )
				reason += "90681 Rotavirus (6 months), ";				
		}
		
		return reason;
	}

	
	/**
	 * Checks to see if a patient needs the Hepatitis B immunization
	 * 
	 * @param count which immunization they are on
	 * @param patientAge how old the patient is
	 * @param time what the current date is
	 * @return when the immunization should be given
	 */
	public static String testHepB(int count, Date patientDOB, long time) {
		String reason = "";
		long weeks = (Calendar.getInstance().getTimeInMillis() - time) / (1000 * 60 * 60 * 24 * 7);
		
		if (0 == count) {
			if (olderThan(patientDOB, 0, 0, 0))
				reason += "90371 Hepatitis B (birth), ";
		}
		else if (1 == count) {
			if (olderThan(patientDOB, 0, 1, 0) && 4 <= weeks)
				reason += "90371 Hepatitis B (1 month), ";
		}
		else if (2 == count) {
			if (olderThan(patientDOB, 0, 6, 0) && 8 <= weeks)
				reason += "90371 Hepatitis B (6 months), ";				
		}
		
		return reason;
	}
	
	private static boolean olderThan(Date patientDOB, int years, int months, int weeks) {
		return endBefore(patientDOB, new Date(), years, months, weeks);
	}
	
	private static boolean firstDoseAfter(Date patientDOB, Date ageFirst, int years, int months, int weeks) {
		return endBefore(patientDOB, ageFirst, years, months, weeks);
	}
	
	private static boolean endBefore(Date startTime, Date endTime, int years, int months, int weeks) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		cal.add(Calendar.YEAR, years);
		cal.add(Calendar.MONTH, months);
		cal.add(Calendar.HOUR, weeks*7*24);
		
		startTime = cal.getTime();
		
		return (startTime.compareTo(endTime) <= 0); //startTime.before(endTime);
	}
	
}
