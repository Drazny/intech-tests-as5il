package com.intech.comptabilite.service.businessmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.service.exceptions.FunctionalException;

@SpringBootTest
public class ComptabiliteManagerImplTest {

	@Autowired
    private ComptabiliteManagerImpl manager;
	
    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date() );
        vEcritureComptable.setReference( "AC-" + cal.get( Calendar.YEAR ) + "/" + "00001" );

        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        Assertions.assertThrows(FunctionalException.class,
        		() -> {
        		manager.checkEcritureComptableUnit(vEcritureComptable);}
        );        
    }

    @Test
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
       
        Assertions.assertThrows(FunctionalException.class,
        		() -> {
        		manager.checkEcritureComptableUnit(vEcritureComptable);}
        );
    }

    @Test
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        
        Assertions.assertThrows(FunctionalException.class,
        		() -> {
        			manager.checkEcritureComptableUnit(vEcritureComptable);
        		}
        );
                
    }
    
    @Test
    public void checkEcritureComptableUnitRG5() throws Exception {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal( new JournalComptable( "AC", "Achat" ) );
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.setLibelle( "Libelle" );
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            new BigDecimal( 123 ),
	            null
	        )
		);
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            null,
	            new BigDecimal( 123 )
	        )
		);
                
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date() );
        
        vEcritureComptable.setId( 1 );
        vEcritureComptable.setReference( "AC-" + cal.get( Calendar.YEAR ) + "/" + "00001" );

        manager.checkEcritureComptableUnit( vEcritureComptable );
    }
    
    
    @Test
    public void checkECUnitG5WithInvalidDateShouldThrow() {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal( new JournalComptable( "AC", "Achat" ) );
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.setLibelle( "Libelle" );
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            new BigDecimal( 123 ),
	            null
	        )
		);
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            null,
	            new BigDecimal( 123 )
	        )
		);
        
        vEcritureComptable.setId( 1 );
        vEcritureComptable.setReference( "AC-0000" + "/" + "00001" );

        Assertions.assertThrows(FunctionalException.class,
        		() -> {
        			manager.checkEcritureComptableUnit(vEcritureComptable);
        		}
        );
    }
    
    @Test
    public void checkECUnitG5WithInvalidCodeShouldThrow() {
    	EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal( new JournalComptable( "AC", "Achat" ) );
        vEcritureComptable.setDate( new Date() );
        vEcritureComptable.setLibelle( "Libelle" );
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            new BigDecimal( 123 ),
	            null
	        )
		);
        vEcritureComptable.getListLigneEcriture().add(
    		new LigneEcritureComptable( 
    			new CompteComptable( 1 ),
	            null, 
	            null,
	            new BigDecimal( 123 )
	        )
		);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date() );
        
        vEcritureComptable.setId( 1 );
        vEcritureComptable.setReference( "AA" + cal.get( Calendar.YEAR ) + "/" + "00001" );

        Assertions.assertThrows(FunctionalException.class,
        		() -> {
        			manager.checkEcritureComptableUnit(vEcritureComptable);
        		}
        );
    }
}
