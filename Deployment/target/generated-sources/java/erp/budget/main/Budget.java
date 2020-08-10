package erp.budget.main;


import erp.Budget;
import erp.budget.main.Chapter;
import erp.budget.main.ChapterSet;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Budget extends IModelInstance<Budget,Budget> {

    // attributes
    public void setYear( String m_Year ) throws XtumlException;
    public String getYear() throws XtumlException;
    public void setFund( double m_Fund ) throws XtumlException;
    public double getFund() throws XtumlException;


    // operations


    // selections
    default public void addR1_Chapter( Chapter inst ) {}
    default public void removeR1_Chapter( Chapter inst ) {}
    public ChapterSet R1_Chapter() throws XtumlException;


}
