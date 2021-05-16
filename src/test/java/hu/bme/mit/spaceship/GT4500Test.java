package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockStorePrimary;
  private TorpedoStore mockStoreSecondary;
  private GT4500 ship;

  @BeforeEach
  public void init(){

    mockStorePrimary = mock(TorpedoStore.class);
    mockStoreSecondary = mock(TorpedoStore.class);

    this.ship = new GT4500(mockStorePrimary, mockStoreSecondary);
  }


  //1.1 teszteset
  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary).fire(1);
    verify(mockStoreSecondary).isEmpty();
    verify(mockStoreSecondary).fire(1);
  }

  // 1.2 teszteset
  @Test
  public void fireTorpedo_Single_Primary_Failure(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(false);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary).fire(1);
  }

  // 1.3 teszteset
  @Test
  public void fireTorpedo_Single_Primary_Empty(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert

    assertEquals(false, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary, never()).fire(1);
  }

  // 1.4 teszteset
  @Test
  public void fireTorpedo_Single_Secondary_Success(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.fire(1)).thenReturn(true);

    //Act
      // Shot using first store, result irrelevant 
    ship.fireTorpedo(FiringMode.SINGLE);
      // Shot using second store
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(true, result);
    verify(mockStoreSecondary).isEmpty();
    verify(mockStoreSecondary).fire(1);
  }

  // 2.1 teszteset
  @Test
  public void fireTorpedo_All_Failure(){
    //Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStorePrimary.fire(1)).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(false, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary).fire(1);
    verify(mockStoreSecondary).isEmpty();
    verify(mockStoreSecondary, never()).fire(1);
  }

  // Forráskód alapján
  @Test
  public void fireTorpedo_Single_Secondary_Empty(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);
    
    // Act 
    ship.fireTorpedo(FiringMode.SINGLE);
      // Az utolsó töltényt lőttük el, ez a tár is kiürül
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockStorePrimary, times(2)).isEmpty();
    verify(mockStoreSecondary).isEmpty();
    verify(mockStoreSecondary, never()).fire(1);
  }

  // Hiányzó ág
  @Test
  public void fireTorpedo_Single_Secondary_Empty_Fire(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(false);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);
    when(mockStorePrimary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockStorePrimary, times(2)).isEmpty();
    verify(mockStoreSecondary).isEmpty();
    verify(mockStorePrimary, times(2)).fire(1);
    verify(mockStoreSecondary, never()).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Stores_Empty(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(true);
    when(mockStoreSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStoreSecondary).isEmpty();
    verify(mockStorePrimary, never()).fire(1);
    verify(mockStoreSecondary, never()).fire(1);
  }

  @Test
  public void fireTorpedo_All_Primary_Empty(){
    // Arrange
    when(mockStorePrimary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockStorePrimary).isEmpty();
    verify(mockStorePrimary, never()).fire(1);
  }

}
