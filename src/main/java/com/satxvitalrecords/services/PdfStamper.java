package com.satxvitalrecords.services;

import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.*;
import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.MailingAddress;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;


/**
 * Simple filling out form example.
 */

@Service
public class PdfStamper {

    @Value("${file-upload-path}")
    private String uploadPath;


    public String SRC;

    public String DEST;


//    public static void main(String args[]) throws IOException {

    public void preparePdf(Record record, Application app, User user, MailingAddress address, Long millis){
        System.out.println(SRC);
        SRC = uploadPath + "/COSA-Mail-Application.pdf";
        DEST = "/edited_COSA-Mail-Application" + millis + ".pdf";
        File file = new File(DEST);
        try {
            file.createNewFile();
            System.out.println(file.getAbsolutePath());
        }catch(IOException e){
            System.out.println("create new file not working");
            e.printStackTrace();
        }

//        file.getParentFile().mkdirs();
        try {
            new PdfStamper().manipulatePdf(SRC, file.getAbsolutePath(), record, app, user, address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void manipulatePdf(String src, String dest, Record record, Application app, User user, MailingAddress address) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfDocument pdf = new PdfDocument(reader, new PdfWriter(dest));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);


//  -- START OF ASSIGNING RECORD OBJECT DETAILS TO FIELDS IN PDF ----

        form.getFormFields().get("First Name").setValue(record.getFirst_name());
        form.getFormFields().get("Middle Name").setValue(record.getMid_name());
        form.getFormFields().get("Last Name").setValue(record.getLast_name());

        if (app.getRecord_type() == "Birth") {
// PARSING DOB IN RECORD DB_TABLE TO POPULATE SEPARATE FIELDS
            String db_month = record.getDate_of_birth().toString().substring(5, 7);
            String db_day = record.getDate_of_birth().toString().substring(8, 10);
            String db_year = record.getDate_of_birth().toString().substring(0, 4);
// ------------  END OF PARSING ----------------------------

            PdfFormField month = form.getFormFields().get("Month").setValue(db_month);
            PdfFormField day = form.getFormFields().get("Day").setValue(db_day);
            PdfFormField year = form.getFormFields().get("Year").setValue(db_year);

            form.getFormFields().get("City or Town").setValue(record.getBirth_city());
            form.getFormFields().get("County").setValue(record.getBirth_county());
        } else if(app.getRecord_type()=="Death"){
            // PARSING date of death IN RECORD DB_TABLE TO POPULATE SEPARATE FIELDS
            String dod_month = record.getDate_of_death().toString().substring(5, 7);
            String dod_day = record.getDate_of_death().toString().substring(8, 10);
            String dod_year = record.getDate_of_death().toString().substring(0, 4);
// ------------  END OF PARSING ----------------------------

            form.getFormFields().get("Month").setValue(dod_month);
            form.getFormFields().get("Day").setValue(dod_day);
            form.getFormFields().get("Year").setValue(dod_year);
            form.getFormFields().get("City or Town").setValue(record.getDeath_city());
            form.getFormFields().get("County").setValue(record.getDeath_county());

        }


        String gender = record.getSex().substring(1);


        form.getFormFields().get("Sex").setValue(gender);
        form.getFormFields().get("First Name_2").setValue(record.getParent1_first_name());
        form.getFormFields().get("Middle Name_2").setValue(record.getParent1_mid_name());
        form.getFormFields().get("Maiden NameLast Name").setValue(record.getParent1_last_name());
        form.getFormFields().get("First Name_3").setValue(record.getParent2_first_name());
        form.getFormFields().get("Middle Name_3").setValue(record.getParent2_mid_name());
        form.getFormFields().get("Maiden NameLast Name_2").setValue(record.getParent2_last_name());

//  -- START OF ASSIGNING APP/USER OBJECT DETAILS TO FIELDS IN PDF ----


//  BINDING APPLICANT INFO TO PDF
        form.getFormFields().get("Applicant Name").setValue(getApplicantName(app));

        form.getFormFields().get("Telephone").setValue(user.getPhone_num());
        form.getFormFields().get("Email Address").setValue(user.getEmail());
        form.getFormFields().get("Street Address").setValue(getAppAddress(app));
        form.getFormFields().get("City").setValue(app.getCity());
        form.getFormFields().get("State").setValue(app.getState());
        form.getFormFields().get("Zip").setValue(app.getZip());
        form.getFormFields().get("Relationship to person listed above").setValue(app.getRecord_relationship());
        form.getFormFields().get("Purpose for obtaining this record").setValue(app.getPurpose());

//        BINDING MAILING ADDRESS TO PDF
// try to get authorizing mailing to the address below to populate

        String mailing_name = app.getFirst_name() + " " + app.getLast_name();

        form.getFormFields().get("Name of Person Receiving Copies if Different from Applicant").setValue(mailing_name);
        form.getFormFields().get("Mailing Address for Copies if Different from Applicant").setValue(getMailingAddress(address));
        form.getFormFields().get("City_2").setValue(address.getCity());
        form.getFormFields().get("State_2").setValue(address.getState());
        form.getFormFields().get("Zip_2").setValue(address.getZip());

//  flattenFields function below disables the ability for the user to edit form after being populated
//        form.flattenFields();
        pdf.close();


    }

//    HELPER FUNCTION TO RETURN NAME WITH NO NULLS
    private static String getApplicantName(Application app){
        if(app.getMid_name() == null){
            return app.getFirst_name() + " " + app.getLast_name();
        }
        return app.getFirst_name() + " " + app.getMid_name() + " " + app.getLast_name();
    }

    //    HELPER FUNCTION TO RETURN APP ADDRESS WITH NO NULLS
    private static String getAppAddress(Application app){
        if(app.getStreet2() == null){
            return app.getStreet();
        }
        return app.getStreet() + " " + app.getStreet2();
    }

    //    HELPER FUNCTION TO RETURN MAILING ADDRESS WITH NO NULLS
    private static String getMailingAddress(MailingAddress address){
        if(address.getStreet_2() == null){
            return address.getStreet();
        }
        return address.getStreet() + " " + address.getStreet_2();
    }



}