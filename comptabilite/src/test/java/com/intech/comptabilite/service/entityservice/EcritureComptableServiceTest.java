package com.intech.comptabilite.service.entityservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.service.exceptions.FunctionalException;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@SpringBootTest
public class EcritureComptableServiceTest {
	
	@Autowired
	private EcritureComptableService ecritureComptableService;

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assertions.assertTrue(ecritureComptableService.isEquilibree(vEcriture));

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assertions.assertFalse(ecritureComptableService.isEquilibree(vEcriture));
    }

    @Test
    public void getDebitTest() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle( "Non équilibrée" );
        vEcriture.getListLigneEcriture().add( this.createLigne( 1, "10", null ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 1, "20", "1" ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 2, null, "30" ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 2, "1", "2" ) );
        
        assertEquals( new BigDecimal( 31 ), ecritureComptableService.getTotalDebit( vEcriture ) );
    }
    
    @Test
    public void getCreditTest() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle( "Non équilibrée" );
        vEcriture.getListLigneEcriture().add( this.createLigne( 1, "10", null ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 1, "20", "1" ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 2, null, "30" ) );
        vEcriture.getListLigneEcriture().add( this.createLigne( 2, "1", "2" ) );
        
        assertEquals( new BigDecimal( 33 ), ecritureComptableService.getTotalCredit( vEcriture ) );
    }
    
    @Test
    public void getEcritureComptableByNullRefShouldThrow() throws NotFoundException {
        Assertions.assertThrows(NotFoundException.class,
        		() -> {
        			ecritureComptableService.getEcritureComptableByRef( null );
        		}
        );    	
    }
    
    @Test
    public void getEcritureComptableByRefTest() throws NotFoundException {
        EcritureComptable ec = ecritureComptableService.getEcritureComptableByRef( "AC-2016/00001" );
        Assertions.assertNotNull( ec );
    }
}
