package edu.scau.mis.pos629;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.*;
@SpringBootApplication
@MapperScan("edu.scau.mis.pos629.mapper")
public class Pos629Application {

	public static void main(String[] args) {
		//启动Swing界面 OOAD课程使用
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			}
		});
		SpringApplication.run(Pos629Application.class, args);
	}

}
