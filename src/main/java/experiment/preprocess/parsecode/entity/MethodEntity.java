package experiment.preprocess.parsecode.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class MethodEntity {
	
	@XmlElement(name = "MethodName")
	private String methodName;

	@XmlElement(name = "MethodComment")
	private String methodComment;
	
	@XmlElement(name = "ReturnType")
	private String returnType;
	
	@XmlElementWrapper(name = "ParameterList")
	@XmlElement(name = "Parameter")
//	@XmlElement(name = "ParameterName")
	private List<ParameterEntity> parametersList;

	@XmlElementWrapper(name = "InnerVarList")
	@XmlElement(name = "InnerVar")
	private List<String> innerVarList;

	@XmlElementWrapper(name = "InnerMethodInvokeList")
	@XmlElement(name = "InnerMethodInvoke")
	private List<String> innerMethodInvokeList;

	
	@XmlElementWrapper(name = "ThrowExceptionList")
	@XmlElement(name = "ExceptionType")
	private List<String> throwException;

	public MethodEntity() {
		super();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodComment() {
		return methodComment;
	}

	public void setMethodComment(String methodComment) {
		this.methodComment = methodComment;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<ParameterEntity> getParametersList() {
		return parametersList;
	}

	public void setParametersList(List<ParameterEntity> parametersList) {
		this.parametersList = parametersList;
	}

	public List<String> getThrowException() {
		return throwException;
	}

	public void setThrowException(List<String> throwException) {
		this.throwException = throwException;
	}

	public List<String> getInnerVarList() {
		return innerVarList;
	}

	public void setInneVarList(List<String> innerVarList) {
		this.innerVarList = innerVarList;
	}

	public List<String> getInnerMethodInvokeList() {
		return innerMethodInvokeList;
	}

	public void setInnerMethodInvokeList(List<String> innerMethodInvokeList) {
		this.innerMethodInvokeList = innerMethodInvokeList;
	}
	
}
