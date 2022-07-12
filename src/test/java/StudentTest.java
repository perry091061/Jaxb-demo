import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class StudentTest {

    private JAXBContext context;

    @Before
    public void setup() throws JAXBException {
        context = JAXBContext.newInstance(Student.class);
    }

    @Test
    public void writesStudentDataToXMLFile() throws JAXBException {
        Marshaller marshaller = context.createMarshaller(); //
        Student studentOut = new Student(1L,"Babbage","Charles", LocalDate.of(1791,12,26));
        marshaller.marshal( studentOut, new File("./src/test/resources/Student.xml"));
        assertTrue(new File("./src/test/resources/Student.xml").exists());

    }

    @Test
    public void readsStudentDataFromAnXMLFile() throws JAXBException, FileNotFoundException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Student studentIn = (Student) unmarshaller.unmarshal(new FileReader("./src/test/resources/Student.xml"));
        assertNotNull(studentIn);
        System.out.println(studentIn);

    }
}