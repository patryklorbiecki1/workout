package pl.org.workout.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(Class<?> clazz){
        super(clazz.getName() + " not found");
    }
}
