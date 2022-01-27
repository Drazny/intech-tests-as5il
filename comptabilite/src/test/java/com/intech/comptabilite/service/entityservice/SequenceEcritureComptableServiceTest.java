package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@SpringBootTest
public class SequenceEcritureComptableServiceTest {

	@Autowired
	SequenceEcritureComptableService secService;
	
	@Test
	public void getDernierValeurByCodeAndAnneeWithInvalidDateShouldThrow() throws NotFoundException {
		Assertions.assertThrows( NotFoundException.class,
				() -> {
					secService.getDernierValeurByCodeAndAnnee( "AC", 3000 );
				}
		);
	}
	
	@Test
	public void getDernierValeurByCodeAndAnneeTest() throws NotFoundException {
		Integer res = secService.getDernierValeurByCodeAndAnnee( "AC", 2016 );
		assertEquals( res, 40 );
	}
}
