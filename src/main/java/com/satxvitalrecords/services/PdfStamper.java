package com.satxvitalrecords.services;

//import com.itextpdf.forms.fields.PdfTextFormField;

import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.PdfAcroForm;
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.test.annotations.WrapToTest;
import com.itextpdf.kernel.pdf.*;
import com.satxvitalrecords.SatxvitalrecordsApplication;
import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.AddressRepo;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Simple filling out form example.
 */

@WrapToTest
@Service
public class PdfStamper {


    public static final String SRC = "src/main/resources/pdf/COSA-Mail-Application.pdf";

    public static final String DEST = "src/main/resources/pdf/edited_COSA-Mail-Application.pdf";


//    public static void main(String args[]) throws IOException {

    public void preparePdf(Record record, Application app, User user){
        File file = new File(DEST);

        file.getParentFile().mkdirs();
        try {
            new PdfStamper().manipulatePdf(SRC, DEST, record, app, user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void manipulatePdf(String src, String dest, Record record, Application app, User user) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfDocument pdf = new PdfDocument(reader, new PdfWriter(dest));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);


//  -- START OF ASSIGNING RECORD OBJECT DETAILS TO FIELDS IN PDF ----

        PdfFormField fn = form.getFormFields().get("First Name").setValue(record.getFirst_name());
        PdfFormField mn = form.getFormFields().get("Middle Name").setValue(record.getMid_name());
        PdfFormField ln = form.getFormFields().get("Last Name").setValue(record.getLast_name());

// PARSING DOB IN RECORD DB_TABLE TO POPULATE SEPARATE FIELDS
        String db_month = record.getDate_of_birth().toString().substring(5,7);
        String db_day = record.getDate_of_birth().toString().substring(8,10);
        String db_year = record.getDate_of_birth().toString().substring(0,4);
// ------------  END OF PARSING ----------------------------

        PdfFormField month = form.getFormFields().get("Month").setValue(db_month);
        PdfFormField day = form.getFormFields().get("Day").setValue(db_day);
        PdfFormField year = form.getFormFields().get("Year").setValue(db_year);
        PdfFormField sex = form.getFormFields().get("Sex").setValue(record.getSex());
        PdfFormField city = form.getFormFields().get("City or Town").setValue(record.getBirth_city());
        PdfFormField county = form.getFormFields().get("County").setValue(record.getBirth_county());
        PdfFormField parent1_fn = form.getFormFields().get("First Name_2").setValue(record.getParent1_first_name());
        PdfFormField parent1_mn = form.getFormFields().get("Middle Name_2").setValue(record.getParent1_mid_name());
        PdfFormField parent1_ln = form.getFormFields().get("Maiden NameLast Name").setValue(record.getParent1_last_name());
        PdfFormField parent2_fn = form.getFormFields().get("First Name_3").setValue(record.getParent2_first_name());
        PdfFormField parent2_mn = form.getFormFields().get("Middle Name_3").setValue(record.getParent2_mid_name());
        PdfFormField parent2_ln = form.getFormFields().get("Maiden NameLast Name_2").setValue(record.getParent2_last_name());

//  -- START OF ASSIGNING APP/USER OBJECT DETAILS TO FIELDS IN PDF ----


//  BINDING APPLICANT NAME TO PDF
        form.getFormFields().get("Applicant Name").setValue(getApplicantName(app));

        form.getFormFields().get("Telephone").setValue(user.getPhone_num());






//        String fname = recordDao.findOne(1L).getFirst_name();
//        String fname = "sarah";

//        System.out.println(fn);
//        System.out.println(ln);

        pdf.close();


    }

//    HELPER FUNCTION TO RETURN NAME WITH NO NULLS
    private static String getApplicantName(Application app){
        if(app.getMid_name() == null){
            return app.getFirst_name() + " " + app.getLast_name();
        }
        return app.getFirst_name() + " " + app.getMid_name() + " " + app.getLast_name();
    }

}