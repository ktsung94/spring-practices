package com.douzone.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	// before 설정 후 포인트 컷 지정
	@Before("execution(com.douzone.aoptest.vo.ProductVo com.douzone.aoptest.service.ProductService.find(String))")
	public void beforeAdvice() {
		System.out.println("--- before Advice ---");
	}

	//(..)은 파라미터 생략
	@After("execution(* *..*.service.ProductService.*(..))")
	public void afterAdvice() {
		System.out.println("--- after Advice ---");
	}

	@AfterReturning("execution(* *..*.service.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("--- afterReturning Advice ---");
	}

	//	@AfterThrowing("execution(* *..*.service.ProductService.*(..))")
	//	public void afterThrowingAdvice() {
	//		System.out.println("--- afterThrowing Advice ---");
	//	}

	// 모든 패키지 모든 클래스 모든 함수에서 발생 = 글로벌 익셉션과 동일
	@AfterThrowing(value = "execution(* *..*.*.*(..))", throwing = "ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("--- afterThrowing Advice : " + ex + "---");
	}

	@Around("execution(* *..*.service.ProductService.*(..))")
	public Object roundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before advice
		System.out.println("@Around(before) advice");

		// PointCut Method 실행
		//Object[] params = {"Camera"};	// 파라미터 가로채기(바꿔버리기)
		//Object result = pjp.proceed(params);
		Object result = pjp.proceed();
		
		// after advice
		System.out.println("@Around(after) advice");
		
		return result;

	}
}
