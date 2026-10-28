package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TorpedoStoreTest {

  private TorpedoStore store;

  @BeforeEach
  public void init() {
    store = new TorpedoStore(10);
  }

  @Test
  public void fire_Success() {
    boolean result = store.fire(1);
    assertTrue(result);
    assertEquals(9, store.getTorpedoCount());
  }

  @Test
  public void fire_EmptyStore() {
    TorpedoStore emptyStore = new TorpedoStore(0);
    assertThrows(IllegalArgumentException.class, () -> {
      emptyStore.fire(1);
    });
  }

  @Test
  public void fire_FailureRateAlwaysFails() {
    TorpedoStore failingStore = new TorpedoStore(5, 1.0);
    boolean result = failingStore.fire(1);
    assertFalse(result);
    assertEquals(5, failingStore.getTorpedoCount());
  }

  @Test
  public void isEmpty_Check() {
    assertFalse(store.isEmpty());
    TorpedoStore emptyStore = new TorpedoStore(0);
    assertTrue(emptyStore.isEmpty());
  }
}