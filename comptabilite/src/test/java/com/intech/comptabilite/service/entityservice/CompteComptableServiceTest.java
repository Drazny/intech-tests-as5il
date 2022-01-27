package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.repositories.CompteComptableRepository;

@SpringBootTest
public class CompteComptableServiceTest {

	@Autowired
	private CompteComptableService ccService;
	@MockBean
	private CompteComptableRepository ccRepository;
	
	@Test
	public void getByNumeroTest() {
		int ccNumber = (int)Math.random();
		CompteComptable cc;
		cc = new CompteComptable( ccNumber );
		
		ArrayList<CompteComptable> list = new ArrayList<CompteComptable>();
		list.add( cc );

		assertEquals( ccService.getByNumero( list, ccNumber ), cc );
	}
	
	@Test
	public void getListCompteComptableTest() {
		ArrayList<CompteComptable> list = new ArrayList<CompteComptable>();
		int ccNumber = (int)Math.random();
		CompteComptable cc;
		cc = new CompteComptable( ccNumber );
		list.add( cc );
		
		Mockito.when( ccRepository.findAll() ).thenReturn( list );
		
		List<CompteComptable> res = ccService.getListCompteComptable();
		assertEquals( list.size(), res.size() );
	}
}
