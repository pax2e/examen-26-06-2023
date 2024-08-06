package ObjectgrafenTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import Objectgrafen.*;

import org.junit.jupiter.api.Test;

class TestSuite {

	@Test
	void ObjectKnoop() {
		LijstObjectKnoop l = new LijstObjectKnoop();
		assertTrue(l.getElements().isEmpty());
		
		LijstObjectKnoop m = new LijstObjectKnoop();
		l.setAt(0, m);
		assertEquals(m, l.getElements().get(0));
		assertTrue(m.getVerwijzers().contains(l));
		
		TekstObjectKnoop tok = new TekstObjectKnoop("Ha");
		assertEquals("Ha", tok.getTekst());

		l.setAt(1, tok);
		ArrayList<ObjectKnoop> expected = new ArrayList<ObjectKnoop>();
		expected.add(m);
		expected.add(tok);
		assertEquals(expected, l.getElements());
		assertEquals(tok, l.getElements().get(1));
		assertTrue(tok.getVerwijzers().contains(l));
		assertEquals(m, l.getElements().get(0));
		assertTrue(m.getVerwijzers().contains(l));
		
		l.removeAt(0);
		expected.remove(0);
		assertEquals(expected, l.getElements());
		assertTrue(tok.getVerwijzers().contains(l));
		assertFalse(m.getVerwijzers().contains(l));

		assertThrows(IllegalArgumentException.class, () -> new TekstObjectKnoop(null));

		assertThrows(IllegalArgumentException.class, () -> tok.voegTekstueleVoorstellingToeAan(null));

		assertThrows(IllegalArgumentException.class, () -> tok.heeftZelfdeInhoudAls(null));
		
		StringBuilder b = new StringBuilder();
		m.voegTekstueleVoorstellingToeAan(b);
		assertEquals("[]", b.toString());
		
		TekstObjectKnoop tok2 = new TekstObjectKnoop("Ha");
		LijstObjectKnoop n = new LijstObjectKnoop();
		n.setAt(0, tok);

		assertFalse(tok.heeftZelfdeInhoudAls(m));
		assertTrue(tok.heeftZelfdeInhoudAls(tok2));
		assertTrue(n.heeftZelfdeInhoudAls(l));
		
	}

}
