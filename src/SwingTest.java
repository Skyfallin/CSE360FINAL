import org.junit.Test;

import static org.junit.Assert.*;

public class SwingTest {

    /**
     * Test the menu listener, which should be used throughout the program.
     * Check to ensure File Picker is opened.
     */
    @Test
    public void loadARoster_WithListener_ShouldMakeFilePickerVisible() {
        RosterController controller = Roster.setupApplication();
        assertTrue("File opener should not produce an error!", controller.getRosterView().getFileMenu().getItem(0).isVisible());
    }

    /**
     * Test the action listener, which should enable the about dialog for the abstract button.
     */
    @Test
    public void aboutDialog_ShouldProduceWindow() {
        RosterController controller = Roster.setupApplication();
        controller.getRosterView().getAboutMenu().doClick();
        assertTrue("About dialog should produce a window!", controller.getRosterView().getAboutDialogue().isVisible());
    }
}
