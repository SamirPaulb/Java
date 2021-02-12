import oracle.search.admin.api.ws.client.AdminAPIRuntimeFault;
import oracle.search.admin.api.ws.client.AdminAPIRuntimeFault_Exception;
import oracle.search.admin.api.ws.client.AdminKeyPair;
import oracle.search.admin.api.ws.client.AdminPortType;
import oracle.search.admin.api.ws.client.AdminService;
import oracle.search.admin.api.ws.client.Credentials;
import oracle.search.admin.api.ws.client.ObjectKey;
import oracle.search.admin.api.ws.client.ObjectOutput;
 
import java.util.List;
import java.net.URL;
 
import javax.xml.ws.BindingProvider;
import javax.xml.namespace.QName;
 
public class CreateWebSource
{
  public static void main(String[] args) throws Exception
  {
    System.out.println( "" );
 
    try
    {
      if ( args == null || args.length != 4 )
      {
        System.out.println(
          "Usage:\n  CreateWebSource <webServiceURL> <userName> <password> <webSourceURL>"
        );
      }
      else
      {
        // Get web service URL from command-line arguments
        String webServiceURL = args[0];
        System.out.println( "Using web service URL \"" + webServiceURL + "\"\n" ); 
        
        // Get username and password
        String userName = args[1];
        String password = args[2];
  
        // Get stateless web service client
        AdminPortType adminPort = 
          getStatelessWebServiceClient( webServiceURL );
      
        // Create Credentials object for operations
        Credentials credentials = new Credentials();
        credentials.setUserName( userName );
        credentials.setPassword( password );
 
        // 1. Create a simple web source
        String webSourceURL = args[3];
        String webSourceXML = 
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<search:config productVersion=\"11.1.2.0.0\" xmlns:search=\"http://xmlns.oracle.com/search\">" +
"  <search:sources>" +
"    <search:webSource>" +
"      <search:name>web1</search:name>" +
"        <search:startingUrls>" +
"          <search:startingUrl>" +
"            <search:url>" + webSourceURL + "</search:url>" +
"          </search:startingUrl>" +
"        </search:startingUrls>" +
"      </search:webSource>" +
"  </search:sources>" +
"</search:config>";
 
        adminPort.createAll(
          "source",
          webSourceXML,
          "password",
          credentials,
          null,
          null,
          "en" 
        );
 
        // 2. Export all sources to show the full definition
        ObjectOutput oo = adminPort.exportAll(
          "source",
          null,
          "password",
          credentials,
          null,
          "en"
        );
        System.out.println("Web Source XML = \n" + oo.getObjectXML() );
 
        // 3. Create a source group for the source
        String sourceGroupXML = 
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<search:config productVersion=\"11.1.2.0.0\" xmlns:search=\"http://xmlns.oracle.com/search\">" +
"  <search:sourceGroups>" +
"    <search:sourceGroup>" +
"      <search:name>Web</search:name>" +
"      <search:assignedSources>" +
"        <search:assignedSource>web1</search:assignedSource>" +
"      </search:assignedSources>" +
"    </search:sourceGroup>" +
"  </search:sourceGroups>" +
"</search:config>";
 
        adminPort.createAll(
          "sourceGroup",
          sourceGroupXML,
          null,
          credentials,
          null,
          null,
          "en"
        );
 
        System.out.println("Created source group...");
        
        // 4. Create a schedule for the web source
        String scheduleXML = 
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<search:config productVersion=\"11.1.2.0.0\" xmlns:search=\"http://xmlns.oracle.com/search\">" +
"   <search:schedules>" +
"      <search:schedule>" +
"         <search:name>schedule1</search:name>" +
"         <search:crawlingMode>ACCEPT_ALL</search:crawlingMode>" +
"         <search:recrawlPolicy>PROCESS_CHANGED</search:recrawlPolicy>" +
"         <search:frequency>" +
"            <search:manual/>" +
"         </search:frequency>" +
"      <search:assignedSources>" +
"        <search:assignedSource>web1</search:assignedSource>" +
"      </search:assignedSources>" +
"      </search:schedule>" +
"   </search:schedules>" +
"</search:config>";
 
        adminPort.createAll(
          "schedule",
          scheduleXML,
          null,
          credentials,
          null,
          null,
          "en"
        );
 
        System.out.println("Created schedule...");
        
        // 5. Start the schedule
        
        // Create object key for schedule name
        ObjectKey objectKey = new ObjectKey();
        AdminKeyPair keyPair = new AdminKeyPair();
        keyPair.setKeyName( "name" ); // schedules identified by name
        keyPair.setKeyValue( "schedule1" ); // schedule name
        objectKey.getAdminKeyPairs().add( keyPair );
        
        adminPort.start(
          "schedule",
          objectKey,
          null,
          null,
          credentials,
          null,
          null,
          "en"
        );
        
        System.out.println("Started schedule..."); 
        System.out.println("Waiting 30 seconds to get status...");
        Thread.sleep( 30000 );
 
        // 6. Use object key above to get schedule state
        oo = adminPort.getState(
          "schedule",
          objectKey,
          null, // request all state properties
          credentials,
          null,
          "en"
        );
        
        System.out.println("Schedule state XML = " + oo.getObjectXML() );
      }
    }
    catch (AdminAPIRuntimeFault_Exception e)
    {
      AdminAPIRuntimeFault runtimeFault = e.getFaultInfo();
      System.out.println("Caught AdminAPIRuntimeFault");
      System.out.println("  message      = " + runtimeFault.getMessage() );
      System.out.println("  errorCode    = " + runtimeFault.getErrorCode() );
      System.out.println("  causeMessage = " + runtimeFault.getCauseMessage() );
      System.out.println("  stackTrace   = " );
      e.printStackTrace( System.out );
      System.out.println("  causeStackTrace = \n" + runtimeFault.getCauseStackTrace() );
    }
    catch (Throwable t)
    {
      System.out.println("Caught unexpected run-time exception");
      System.out.println("  message    = " + t.getMessage() );
      System.out.println("  stackTrace = " );
      t.printStackTrace( System.out );
    }
  }
 
  /**
   * Initializes and returns a stateless admin web service client.
   */
  private static AdminPortType getStatelessWebServiceClient(
    String webServiceURL) throws Exception
  {
    AdminService adminService = new AdminService(
      new URL( webServiceURL ),
      new QName(
        "http://search.oracle.com/Admin",
        "AdminService"
      )
    );
 
    return adminService.getAdmin();
  }
}
