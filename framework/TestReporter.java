/**
 * TestReporterimpl.java
 * Generates an HTML report of the test results.  The report is divided into three sections: passed, failed, and exception.
 * This is the concrete implementation of TestReporter
 * Part of FINAL
 */

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestReporterImpl implements TestReporter{

    private String fileName;

    public TestReporterImpl(String name){
        fileName = name;
    }

    /**
     * Generate a report in HTML format and write it to an HTML file.
     * @param passed A list of test results of the passed tests
     * @param failed A list of test results of the failed tests
     * @param exception A list of test results of the exception tests
     * @throws FileNotFoundException
     */
    @Override
    public void generateHtml(List<TestResult> passed, List<TestResult> failed, List<TestResult> exception) throws FileNotFoundException {
        File output = new File(fileName);
        PrintStream out = new PrintStream(output);
        out.println("<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {\n  border: 1px solid blue;\n}\n</style>\n</head>\n<body>");
        
        out.println("\n<h2>Passed Tests</h2>");
        makeTable(out, passed);

        out.println("\n<h2>Failed Tests</h2>");
        makeTable(out, failed);
        
        out.println("\n<h2>Exception Tests</h2>");
        makeTable(out, exception);

        out.println("\n</body>\n</html>");
        out.close();
    }
    
    /**
     * creates html code for creating and formatting a table with a set of data
     * @param out Printstream that prints to the file
     * @param list list of TestResults for that table
     */
    private void makeTable(PrintStream out, List<TestResult> list){
        List<TestResult> temp = list;
        out.println("\n<table style=\"width:100%\">\n  <tr>\n    <th>RESULT</th>\n    <th>MESSAGE</th>\n  </tr>");
        out.println("  <tr>");
        for(TestResult tr:temp){
            out.println("  <tr>");
            out.println("    <td>" + tr.result + "</td>");
            if(tr.note == null){
                out.println("    <td></td>"); 
            } else {
                out.println("    <td>" + tr.note + "</td>");
            }
            out.println("  </tr>");
        }
        out.println("</table>");
    }
}
