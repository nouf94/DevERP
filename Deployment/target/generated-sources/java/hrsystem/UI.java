package hrsystem;


import hrsystem.ui.UIApp;
import hrsystem.ui.UIAppOps;
import hrsystem.ui.UIAuthenticate;

import io.ciera.runtime.summit.application.IApplication;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.components.Component;
import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;


public class UI extends Component<UI> {

    private Map<String, Class<?>> classDirectory;

    public UI(IApplication app, IRunContext runContext, int populationId) {
        super(app, runContext, populationId);



        classDirectory = new TreeMap<>();

    }

    // domain functions
    public void CreateEmployee() throws XtumlException {
    }

    public void Reply( final String p_msg,  final boolean p_state ) throws XtumlException {
        context().Reply( p_msg, p_state );
    }

    public void ReplyUsernamePassword( final String p_Username,  final String p_Password,  final boolean p_state ) throws XtumlException {
    }

    public void SendEmployee( final int p_EmployeeID,  final int p_NationalID,  final String p_FirstName,  final String p_MiddleName,  final String p_LastName,  final String p_Gender,  final int p_DateOfBirth,  final String p_Degree,  final int p_StartDate,  final int p_LeaveBalance,  final int p_SickLeaveBalance,  final int p_Size ) throws XtumlException {
        context().SendEmployee( p_EmployeeID, p_NationalID, p_FirstName, p_MiddleName, p_LastName, p_Gender, p_DateOfBirth, p_Degree, p_StartDate, p_LeaveBalance, p_SickLeaveBalance, p_Size );
    }

    public void SendLeaveSpecification( final String p_Name,  final int p_MaximumDays,  final int p_MinimumDays,  final int p_Size ) throws XtumlException {
        context().SendLeaveSpecification( p_Name, p_MaximumDays, p_MinimumDays, p_Size );
    }



    // relates and unrelates


    // instance selections


    // relationship selections


    // ports
    private UIApp UIApp;
    public UIApp App() {
        if ( null == UIApp ) UIApp = new UIApp( this, null );
        return UIApp;
    }
    private UIAppOps UIAppOps;
    public UIAppOps AppOps() {
        if ( null == UIAppOps ) UIAppOps = new UIAppOps( this, null );
        return UIAppOps;
    }
    private UIAuthenticate UIAuthenticate;
    public UIAuthenticate Authenticate() {
        if ( null == UIAuthenticate ) UIAuthenticate = new UIAuthenticate( this, null );
        return UIAuthenticate;
    }


    // utilities


    // component initialization function
    @Override
    public void initialize() throws XtumlException {

    }

    @Override
    public String getVersion() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("UIProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version", "Unknown");
    }
    @Override
    public String getVersionDate() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("UIProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version_date", "Unknown");
    }

    @Override
    public boolean addInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot add empty instance to population." );

        return false;
    }

    @Override
    public boolean removeInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot remove empty instance from population." );

        return false;
    }

    @Override
    public UI context() {
        return this;
    }

    @Override
    public Class<?> getClassByKeyLetters(String keyLetters) {
        return classDirectory.get(keyLetters);
    }

}
