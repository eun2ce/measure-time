package io.github.eun2ce.measuretime;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("io.github.eun2ce.measuretime.MeasureTime")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MeasureTimeProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (Element element : roundEnv.getElementsAnnotatedWith(MeasureTime.class)) {
      if (element.getKind() == ElementKind.METHOD) {
        ExecutableElement methodElement = (ExecutableElement) element;
        TypeElement classElement = (TypeElement) methodElement.getEnclosingElement();

        String className = classElement.getSimpleName() + "_Generated";
        String packageName = processingEnv.getElementUtils().getPackageOf(classElement).toString();

        MethodSpec originalMethod = MethodSpec.overriding(methodElement)
            .addStatement("long start = System.nanoTime()")
            .addStatement("Object result = $N()", methodElement.getSimpleName())
            .addStatement("long end = System.nanoTime()")
            .addStatement("System.out.println($S + (end - start) / 1_000_000.0 + $S)",
                methodElement.getSimpleName() + " executed in ", " ms")
            .addStatement("return result")
            .build();

        TypeSpec generatedClass = TypeSpec.classBuilder(className)
            .addModifiers(Modifier.PUBLIC)
            .addMethod(originalMethod)
            .build();

        JavaFile javaFile = JavaFile.builder(packageName, generatedClass).build();

        try {
          javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
}
