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

    @Autowired
    private ApplicationRepo appDao;
    @Autowired
    private UserRepo userDao;
    @Autowired
    private RecordRepo recordDao;
    @Autowired
    private AddressRepo mailDao;


    public static final String SRC = "src/main/resources/pdf/COSA-Mail-Application.pdf";

    public static final String DEST = "src/main/resources/pdf/edited_COSA-Mail-Application.pdf";


//    public static void main(String args[]) throws IOException {

    public void preparePdf(String name){
        File file = new File(DEST);

        file.getParentFile().mkdirs();

        try {
            new PdfStamper().manipulatePdf(SRC, DEST, name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void manipulatePdf(String src, String dest, String name) throws IOException {


        PdfReader reader = new PdfReader(src);

        PdfDocument pdf = new PdfDocument(reader, new PdfWriter(dest));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

        PdfFormField tf = form.getFormFields().get("First Name");
//        String fname = recordDao.findOne(1L).getFirst_name();
//        String fname = "sarah";
        System.out.println(name);
        tf.setValue(name);


        pdf.close();


    }

}