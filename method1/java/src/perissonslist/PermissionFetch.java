package perissonslist;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

////////////////////////////////////////////////////////



//          RESTRICTED MODEL ANALYSIS
//          GRAPH




/////////////////////////////////////////////////////////


public class PermissionFetch {

    static String path = null;

    public PermissionFetch(){}


    //method to find Android MANIFEST file

    public static String getManifestFile(File dir){

        File[] files = dir.listFiles();
        for (File file : files){
            /*if(file.isDirectory()){
                getManifestFile(file);
                //System.out.println(file.getName());
            }*/

            if(file.isFile()){
                if(file.getName().equals("AndroidManifest.xml")){
                    path = file.getPath();


                }
            }
        }

        return path;
    }


    //XML parsing StAX
    public static List<String> readPermissions(String path){

        List<String> permissionsList = new ArrayList<String>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(
                            new FileReader(path));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                        StartElement startElement = event.asStartElement();
                        String child = startElement.getName().getLocalPart();
                        if (child.equalsIgnoreCase("uses-permission")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String permissions = attributes.next().getValue();
                            System.out.println(permissions);
                            permissionsList.add(permissions);
                        }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return permissionsList;
    }

    /*
    public static void main(String[] args) {

        List<String> permissions = new ArrayList<String>();
        File dir = new File("/home/j0nee/Acadamics/Trojan/malware_collection/code/method1/apks/krep.itmtd.ywtjexf-1");
        System.out.print(getManifestFile(dir));
        permissions = readPermissions(getManifestFile(dir));
        System.out.println(permissions);

     }
    */
}



