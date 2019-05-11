package codes.fepi.ldfspark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a class with this annotation. <br/>
 * All methods of the annotated class have to be public to be detected, and have to return something. <br/>
 * Possible Parameters are:
 * <ul>
 *     <li>nothing</li>
 *     <li>spark.Request</li>
 *     <li>spark.Request, spark.Response</li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageHandler {

}