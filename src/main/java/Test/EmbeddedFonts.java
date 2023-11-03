/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author 84374
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import org.apache.pdfbox.pdmodel.common.PDRectangle;

import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;



import org.apache.pdfbox.pdmodel.PDPage;



import org.apache.pdfbox.pdmodel.PDPageContentStream;



import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * An example of using an embedded TrueType font with Unicode text.
 *
 * @author Keiji Suzuki
 * @author John Hewson
 */
public final class EmbeddedFonts
{

    private EmbeddedFonts()
    {
    }
    
    public static void main(String[] args) throws IOException
    {
        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            String dir = "C:\\Users\\84374\\Desktop\\VietFontsWeb1_ttf\\vuArial.ttf";
            PDType0Font font = PDType0Font.load(document, new File(dir));
            
            try (PDPageContentStream stream = new PDPageContentStream(document, page))
            {
                stream.beginText();
                stream.setFont(font, 12);
                stream.setLeading(12 * 1.2f);

                stream.newLineAtOffset(50, 600);
                stream.showText("PDFBox's Unicode with Embedded TrueType Font");
                stream.newLine();
                
                stream.showText("Supports full Unicode text â˜º");
                stream.newLine();
                
                stream.showText("English Ñ€ÑƒÑÑÐºÐ¸Ð¹ ÑÐ·Ñ‹Ðº Tiáº¿ng Viá»‡t");
                stream.newLine();
                
                // ligature
                stream.showText("Ligatures: \uFB01lm \uFB02ood");
                
                stream.endText();
            }
            
            document.save("example.pdf");
        }
    }
}
