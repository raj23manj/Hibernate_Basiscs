package com.rajesh.springdata.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rajesh.springdata.files.entites.Image;
import com.rajesh.springdata.files.repos.ImageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiledataApplicationTests {
	@Autowired
	ImageRepository repository;

	@Test
	public void testImageSave() throws IOException {
		Image img = new Image();
		img.setId(1L);
		img.setName("testIMage.JPG");
		
		File file = new File("/Users/rmanjunath/Desktop/java/petclinic/sfg-pet-clinic/pet-clinic-web/src/main/resources/spring.png");
		byte fileContent[] = new byte[(int)file.length()];
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(fileContent);
			img.setData(fileContent);
			
			repository.save(img);
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void tesReadImage() throws IOException {
		Image image = repository.findById(1L).get();
		File file = new File("/Users/rmanjunath/Desktop/java/" + image.getName());
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(image.getData());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fos.close();
		}
		
	}

}
