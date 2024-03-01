import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * 
 * @author Michael Kölling and Josh Murphy
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;           // the Graphical User Interface
    private Portfolio portfolio;             // Used to store the portfolio data.
    private int propertyNumber;              // Used to index between the properties.
    private Property currentProperty;        // Used to store the current property.
    private int numberOfPropertiesViewed;    // Use to store the number of properties viewed.
    private int viewedPropertiesTotalPrice;  // Used to store the total price of the properties viewed.
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        
        this.propertyNumber = 0;
        this.numberOfPropertiesViewed = 1;
        this.currentProperty = portfolio.getProperty(this.propertyNumber);
        this.viewedPropertiesTotalPrice = this.currentProperty.getPrice();
        gui.showID(this.currentProperty);
        gui.showProperty(this.currentProperty);
        gui.showFavourite(this.currentProperty);
    }

    /**
     * Show the next property's information on screen.
     * Show if this property is favourited.
     * Increment the numberOfPropertiesViewed variable by 1.
     * Add the current property price to viewedPropertiesTotalPrice.
     */
    public void nextProperty()
    {
        // We first check if we've reached the end of the list.
        if (this.propertyNumber + 1 < portfolio.numberOfProperties())
        {
            this.propertyNumber += 1;
        } else
        {
            this.propertyNumber = 0;
        }
        this.currentProperty = portfolio.getProperty(this.propertyNumber);
        gui.showID(this.currentProperty);
        gui.showProperty(this.currentProperty);
        gui.showFavourite(this.currentProperty);
        this.numberOfPropertiesViewed++;
        viewedPropertiesTotalPrice += this.currentProperty.getPrice();
    }

    /**
     * Show the previous property's information on screen.
     * Show if this property is favourited.
     * Increment the numberOfPropertiesViewed variable by 1.
     * Add the current property price to viewedPropertiesTotalPrice.
     */
    public void previousProperty()
    {
        // We first check if we're at the first property, if yes we stop.
        if (this.propertyNumber > 0)
        {
            this.propertyNumber -= 1;
        } else
        {
            this.propertyNumber = portfolio.numberOfProperties() - 1;
        }
        this.currentProperty = portfolio.getProperty(this.propertyNumber);
        gui.showID(this.currentProperty);
        gui.showProperty(this.currentProperty);
        gui.showFavourite(this.currentProperty);
        this.numberOfPropertiesViewed++;
        viewedPropertiesTotalPrice += this.currentProperty.getPrice();
    }

    /**
     * toggle the current property's isFavourite variable and display it.
     */
    public void toggleFavourite()
    {
        this.currentProperty.toggleFavourite();
        gui.showFavourite(this.currentProperty);
    }
    

    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = this.currentProperty.getLatitude();
       double longitude = this.currentProperty.getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * Return the number of properties viewed over the course of the program.
     */
    public int getNumberOfPropertiesViewed()
    {
        return this.numberOfPropertiesViewed;
    }
    
    /**
     * Calculate and return the average property price.
     * This is done by dividing viewedPropertiesTotalPrice by numberOfPropertiesViewed.
     */
    public int averagePropertyPrice()
    {
        return (this.viewedPropertiesTotalPrice / this.numberOfPropertiesViewed);
    }
}
