package net.larbig.rs;

/**
 * A simple CDI service which is able to say hello to someone
 * 
 * @author Pete Muir
 * 
 */
public class LaboService {

   String createHelloMessage(String name) {
      return "Hello " + name + "!";
   }

}
