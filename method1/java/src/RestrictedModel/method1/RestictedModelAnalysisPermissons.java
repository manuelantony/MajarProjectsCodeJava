package RestrictedModel.method1;

import perissonslist.PermissionFetch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RestictedModelAnalysisPermissons {


    //access permissions from PermissionFetch class
    //categories to what type of app; here by default it is Banking app :: This data should collect using crowd sourcing
    //collect the data from the needed Banking permissions from a file
    //find out how many unmatching permissions; list the unmatched permissions
    //compare it with standard malicious permissions


    private static PermissionFetch permissionsFetch;


    private  static List<String> collectTrustedPermissionsByType(String type) throws IOException {

        BufferedReader content;
        String line;
        List<String> permissionsTrusted = new ArrayList<String>();
        //iterate through the folder which contain several list of permissions; based on type
        File dir = new File("/home/j0nee/Acadamics/Trojan/malware_collection/code/method1/trustedPermissionsCollection");
        File[] files = dir.listFiles();
        for(File file : files){
            if (file.getName().equals(type)){
                //read contents of file
                content = new BufferedReader(new FileReader(file.getPath()));
                //ignore lines start with "//"
                while ((line = content.readLine()) != null){
                    permissionsTrusted.add(line);
                }

            }
        }

        return permissionsTrusted;
    }

    private static void comparetrustedPermissions(List actualPermissions, List trustedPermissions){

        List<String> diffrentPermissions = new ArrayList<String>();

        actualPermissions.removeAll(trustedPermissions);
        diffrentPermissions = actualPermissions;
        System.out.println(diffrentPermissions);
    }

    public static void main(String[] args) throws IOException {

        //access permissions from PermissionFetch class
        permissionsFetch = new PermissionFetch();

        List<String> permissions = new ArrayList<String>();
        List<String> trustedPermissions = new ArrayList<String>();
        File dir = new File("/home/j0nee/Acadamics/Trojan/malware_collection/code/method1/apks/krep.itmtd.ywtjexf-1");
        System.out.print(permissionsFetch.getManifestFile(dir));

        permissions = permissionsFetch.readPermissions(permissionsFetch.getManifestFile(dir));
        System.out.println(permissions);

        //categories to what type of app; here by default it is Banking app :: This data should collect using crowd sourcing
        //This method is not implemented (crowd sourcing)

        trustedPermissions = collectTrustedPermissionsByType("Banking");
        System.out.println(trustedPermissions);

        comparetrustedPermissions(permissions,trustedPermissions);

    }
}
