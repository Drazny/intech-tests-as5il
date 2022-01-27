package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.repositories.JournalComptableRepository;

@SpringBootTest
public class JournalComptableServiceTest {

	@Autowired
	JournalComptableService jcService;
	@MockBean
	JournalComptableRepository jcRepository;
	
	@Test
	public void getByCodeTest() {
		JournalComptable jc;
		jc = new JournalComptable( "AA", "Toto" );
		
		ArrayList<JournalComptable> list = new ArrayList<JournalComptable>();
		list.add( jc );

		assertEquals( jcService.getByCode( list, jc.getCode() ), jc );
	}
	
	@Test
	public void getListJournalComptableTest() {
		ArrayList<JournalComptable> list = new ArrayList<JournalComptable>();
		JournalComptable jc;
		jc = new JournalComptable( "AA", "Toto" );
		list.add( jc );
		
		Mockito.when( jcRepository.findAll() ).thenReturn( list );
		
		List<JournalComptable> res = jcService.getListJournalComptable();
		assertEquals( list.size(), res.size() );
	}
}
