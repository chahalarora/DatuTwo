package com.datu.result;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class ProgramEnrollmentAction implements java.io.Serializable {

  static final long serialVersionUID = 1L;

  private int programId;

  private java.lang.String action;

  public ProgramEnrollmentAction() {
  }

  public int getProgramId() {
    return this.programId;
  }

  public void setProgramId(int programId) {
    this.programId = programId;
  }

  public java.lang.String getAction() {
    return this.action;
  }

  public void setAction(java.lang.String action) {
    this.action = action;
  }

  public ProgramEnrollmentAction(int programId, java.lang.String action) {
    this.programId = programId;
    this.action = action;
  }

}