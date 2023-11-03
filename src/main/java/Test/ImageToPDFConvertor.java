/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

// Import required libraries
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
// Create a class ImageToPDFConvertor

public class ImageToPDFConvertor {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            // Create a new thread
            public void run() {
                try {
                    ImageToPDFConvertor window = new ImageToPDFConvertor();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ImageToPDFConvertor() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    // Create a method initialize to initialize the frame and its components
    private void initialize() {

        // Create a new frame and set its properties
        frame = new JFrame();
        frame.setBounds(100, 100, 491, 231);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create a label for input file
        final JLabel input_label = new JLabel("Input File");
        input_label.setBounds(29, 29, 271, 24);
        frame.getContentPane().add(input_label);

        // Create a label for output file
        final JLabel output_label = new JLabel("Output File");
        output_label.setBounds(29, 86, 271, 24);
        frame.getContentPane().add(output_label);

        // Create a button for selecting input file
        JButton input_button = new JButton("Select Input");

        // Add an action listener to the input button
        input_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Create a new file chooser
                JFileChooser chooser = new JFileChooser();

                // Set the file filter to select only image files 
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Images", "jpeg", "png", "jpg");

                // Show the file chooser dialog
                chooser.setFileFilter(filter);

                // Get the selected file
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();

                // Set the input label to the selected file
                String filename = f.getAbsolutePath();
                input_label.setText(filename);

            }
        });

        // Set the bounds of the input button
        input_button.setBounds(328, 28, 136, 29);
        frame.getContentPane().add(input_button);

        // Create a button for selecting output file
        JButton output_button = new JButton("Select Output");

        // Add an action listener to the output button
        output_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Create a new file chooser dialog box for saving the file
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);

                // Get the provided file name and set the output label to the file name
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    output_label.setText(file.getAbsolutePath() + ".pdf");
                } else {
                    output_label.setText("Save command canceled");
                }
            }
        });

        // Set the bounds of the output button
        output_button.setBounds(328, 81, 136, 29);
        frame.getContentPane().add(output_button);

        // Create a button for converting the image to PDF
        JButton convert = new JButton("Convert to PDF");

        // Add an action listener to the convert button
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    // Create a new file with the provided output file name
                    File pdfFile = new File(output_label.getText());

                    // Create a new PDF document with the provided file name
                    PdfWriter writer = new PdfWriter(new FileOutputStream(pdfFile));
                    PdfDocument pdf = new PdfDocument(writer);

                    // Create a new document and add the image to the document
                    Document document = new Document(pdf, PageSize.A4);
                    Image image = new Image(ImageDataFactory.create(input_label.getText()));
                    document.add(image);
                    document.close();

                    // Show a message dialog box to indicate that the PDF file has been created successfully
                    JOptionPane.showMessageDialog(null, "PDF created successfully at " + pdfFile.getAbsolutePath());
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, "An error occurred while converting the image to PDF: " + ee.getMessage());
                }

            }
        });

        // Set the bounds of the convert button
        convert.setBounds(169, 145, 152, 40);
        frame.getContentPane().add(convert);

        // Create a label for indicating that the input file should be an image file
        JLabel not_label = new JLabel("For image files only");
        not_label.setBounds(194, 6, 114, 16);
        frame.getContentPane().add(not_label);
    }
}
