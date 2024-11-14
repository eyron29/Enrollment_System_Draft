package enrollmentDemo;

//used for custom exception handling
public class InvalidInputException extends Exception{
    public InvalidInputException(String str){
        //Error: + the message for specific error
        System.out.println("Error: " + str);
    }
}
