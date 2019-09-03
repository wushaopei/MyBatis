package com.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.mp.dao")
public class Bootstrap 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
