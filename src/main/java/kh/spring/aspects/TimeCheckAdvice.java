package kh.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheckAdvice
{
//	@Around("execution(* kh.spring.main.HomeController.*(..)) || execution(* kh.spring.dao.*.*(..))")
//	public Object timeCheck(ProceedingJoinPoint pjp)
//	{
//		Object returnObj = null;
//		try
//		{
//			long startTime = System.currentTimeMillis();
//			
//			returnObj = pjp.proceed();
//			
//			long endTime = System.currentTimeMillis();
//			System.out.println((endTime - startTime) / 1000.0 + " 초 동안 수행");
//		}
//		catch(Throwable th)
//		{
//			th.printStackTrace();
//		}
//		return returnObj;
//	}
}
