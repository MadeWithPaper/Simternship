package simternship.simternship;

import android.content.Context;
import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jacky on 3/13/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestStartScreenAndNewGameView {

      @Mock
      StartScreen sc;
      NewGameView ngv;
      Bundle b;

      @Test
      public void readStringFromContext_LocalizedString() {
          sc = mock(StartScreen.class);
          ngv = mock(NewGameView.class);
         sc.signIn("test@gmail.com", "123456");
         when(ngv.getString(R.id.welcomeText)).thenReturn("Welcome Jacky Huang");
         String s = ngv.getString(R.id.welcomeText);
         assertEquals(s, "Welcome Jacky Huang");
      }
   }

