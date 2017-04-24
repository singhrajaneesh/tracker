package com.jainpuja.androidmusicplayer_ver2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author jon
 */
public class Decompress {
    private String _zipFile;
    private String _location;

    public Decompress(String zipFile, String location) {
        _zipFile = zipFile;
        _location = location;
//        UnZip unZip = new UnZip();
//        unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
        //_dirChecker("");
    }

    public void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];

        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                String[] separated = fileName.split("/");
//                separated[0]; // this will contain "Fruit"
//                separated[1];
                File newFile = new File(outputFolder + File.separator + separated[1]);
//                System.out.println("outputFolder : " + outputFolder);
//                System.out.println("fileName : " + fileName);
//                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
//    public void unzip() {
//        try  {
//            FileInputStream fin = new FileInputStream(_zipFile);
//            ZipInputStream zin = new ZipInputStream(fin);
//            ZipEntry ze = null;
//            while ((ze = zin.getNextEntry()) != null) {
//                Log.v("Decompress", "Unzipping " + ze.getName());
//
//                if(ze.isDirectory()) {
//                    _dirChecker(ze.getName());
//                } else {
//                    FileOutputStream fout = new FileOutputStream(_location + ze.getName());
//                    for (int c = zin.read(); c != -1; c = zin.read()) {
//                        fout.write(c);
//                    }
//
//                    zin.closeEntry();
//                    fout.close();
//                }
//
//            }
//            zin.close();
//        } catch(Exception e) {
//            Log.e("Decompress", "unzip", e);
//        }
//
//    }
//
//    private void _dirChecker(String dir) {
//        File f = new File(_location + dir);
//
//        if(!f.isDirectory()) {
//            f.mkdirs();
//        }
//    }


