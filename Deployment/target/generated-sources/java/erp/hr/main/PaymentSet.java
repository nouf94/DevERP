package erp.hr.main;


import erp.hr.main.EmployeeSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface PaymentSet extends IInstanceSet<PaymentSet,Payment> {

    // attributes
    public void setName( String m_Name ) throws XtumlException;
    public void setDate( int m_Date ) throws XtumlException;
    public void setAmount( double m_Amount ) throws XtumlException;
    public void setNational_ID( int ref_National_ID ) throws XtumlException;


    // selections
    public EmployeeSet R3_Employee() throws XtumlException;


}
