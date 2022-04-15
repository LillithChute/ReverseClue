import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Generate some test data for the model to work with as Readable.
 */
public class TestData {

  /**
   * Corto Maltese world building data.
   *
   * @return A readable containing Corto Maltese World.
   */
  public Readable getCortoMalteseData() {
    String data = "40 40 Corto Maltese\n"
            + "75 Starro The Conqueror\n"
            + "Baby Starro\n"
            + "9\n"
            + " 9  2 35  5 Beach Head One\n"
            + " 2  5  8 11 Beach Head Two\n"
            + "10 20 26 23 Town\n"
            + "16  6 30 13 Forest\n"
            + "19 14 25 16 Insurgent Camp\n"
            + "16 24 18 26 Road\n"
            + "14 27 24 30 Palace\n"
            + "31 34 38 38 Guard House\n"
            + "10 33 30 39 Jotunheim\n"
            + "25\n"
            + "0 5 Helicopter\n"
            + "0 3 Mongal\n"
            + "0 1 Detachable Arms\n"
            + "0 2 Boomerang\n"
            + "1 3 Pistol\n"
            + "1 1 Ocean Water\n"
            + "1 1 Conch Shell\n"
            + "2 2 Beer bottle\n"
            + "2 2 Butterfly Knife\n"
            + "2 1 Trash Can\n"
            + "3 1 Small Stick\n"
            + "4 2 Blowgun\n"
            + "4 2 Sling Shot\n"
            + "4 5 Nanaway\n"
            + "5 4 Armored Truck\n"
            + "6 5 Machine Guns\n"
            + "6 4 Antique Pistol\n"
            + "6 3 Javelin\n"
            + "7 2 Blackjack\n"
            + "7 3 Rifle\n"
            + "7 3 Bowie Knife\n"
            + "8 5 C4\n"
            + "8 5 Bloodsport\n"
            + "8 5 Polka-dots\n"
            + "8 6 Rats";

    return new BufferedReader(new StringReader(data));
  }

  /**
   * Corto Maltese world building data with a weak target for testing death.
   *
   * @return A readable containing Corto Maltese World.
   */
  public Readable getCortoMalteseDataWeakTarget() {
    String data = "40 40 Corto Maltese\n"
            + "5 Starro The Conqueror\n"
            + "Baby Starro\n"
            + "9\n"
            + " 9  2 35  5 Beach Head One\n"
            + " 2  5  8 11 Beach Head Two\n"
            + "10 20 26 23 Town\n"
            + "16  6 30 13 Forest\n"
            + "19 14 25 16 Insurgent Camp\n"
            + "16 24 18 26 Road\n"
            + "14 27 24 30 Palace\n"
            + "31 34 38 38 Guard House\n"
            + "10 33 30 39 Jotunheim\n"
            + "25\n"
            + "0 5 Helicopter\n"
            + "0 3 Mongal\n"
            + "0 1 Detachable Arms\n"
            + "0 2 Boomerang\n"
            + "1 3 Pistol\n"
            + "1 1 Ocean Water\n"
            + "1 1 Conch Shell\n"
            + "2 2 Beer bottle\n"
            + "2 2 Butterfly Knife\n"
            + "2 1 Trash Can\n"
            + "3 1 Small Stick\n"
            + "4 2 Blowgun\n"
            + "4 2 Sling Shot\n"
            + "4 5 Nanaway\n"
            + "5 4 Armored Truck\n"
            + "6 5 Machine Guns\n"
            + "6 4 Antique Pistol\n"
            + "6 3 Javelin\n"
            + "7 2 Blackjack\n"
            + "7 3 Rifle\n"
            + "7 3 Bowie Knife\n"
            + "8 5 C4\n"
            + "8 5 Bloodsport\n"
            + "8 5 Polka-dots\n"
            + "8 6 Rats";

    return new BufferedReader(new StringReader(data));
  }

  /**
   * Bad test data to check proper world building.
   *
   * @return Bad world data.
   */
  public Readable badSpaceOffGrid() {
    String data = "40 40 Corto Maltese\n"
            + "75 Starro The Conqueror\n"
            + "Baby Starro\n"
            + "9\n"
            + " 9  2 35  5 Beach Head One\n"
            + " 2  5  8 11 Beach Head Two\n"
            + "10 20 26 23 Town\n"
            + "16  6 30 13 Forest\n"
            + "19 44 25 16 Insurgent Camp\n"
            + "16 24 18 26 Road\n"
            + "14 27 24 30 Palace\n"
            + "31 34 38 38 Guard House\n"
            + "10 33 30 39 Jotunheim\n"
            + "25\n"
            + "0 5 Helicopter\n"
            + "0 3 Mongal\n"
            + "0 1 Detachable Arms\n"
            + "0 2 Boomerang\n"
            + "1 3 Pistol\n"
            + "1 1 Ocean Water\n"
            + "1 1 Conch Shell\n"
            + "2 2 Beer bottle\n"
            + "2 2 Butterfly Knife\n"
            + "2 1 Trash Can\n"
            + "3 1 Small Stick\n"
            + "4 2 Blowgun\n"
            + "4 2 Sling Shot\n"
            + "4 5 Nanaway\n"
            + "5 4 Armored Truck\n"
            + "6 5 Machine Guns\n"
            + "6 4 Antique Pistol\n"
            + "6 3 Javelin\n"
            + "7 2 Blackjack\n"
            + "7 3 Rifle\n"
            + "7 3 Bowie Knife\n"
            + "8 5 C4\n"
            + "8 5 Bloodsport\n"
            + "8 5 Polka-dots\n"
            + "8 6 Rats";

    return new BufferedReader(new StringReader(data));
  }

  /**
   * Bad test data to check proper world building.
   *
   * @return Bad world data.
   */
  public Readable negativeCoordinates() {
    String data = "40 40 Corto Maltese\n"
            + "75 Starro The Conqueror\n"
            + "Baby Starro\n"
            + "9\n"
            + " 9  2 35  5 Beach Head One\n"
            + " 2  5  8 11 Beach Head Two\n"
            + "10 20 26 23 Town\n"
            + "16  6 30 13 Forest\n"
            + "19 14 25 16 Insurgent Camp\n"
            + "16 24 18 26 Road\n"
            + "14 -27 24 30 Palace\n"
            + "31 34 38 38 Guard House\n"
            + "10 33 30 39 Jotunheim\n"
            + "25\n"
            + "0 5 Helicopter\n"
            + "0 3 Mongal\n"
            + "0 1 Detachable Arms\n"
            + "0 2 Boomerang\n"
            + "1 3 Pistol\n"
            + "1 1 Ocean Water\n"
            + "1 1 Conch Shell\n"
            + "2 2 Beer bottle\n"
            + "2 2 Butterfly Knife\n"
            + "2 1 Trash Can\n"
            + "3 1 Small Stick\n"
            + "4 2 Blowgun\n"
            + "4 2 Sling Shot\n"
            + "4 5 Nanaway\n"
            + "5 4 Armored Truck\n"
            + "6 5 Machine Guns\n"
            + "6 4 Antique Pistol\n"
            + "6 3 Javelin\n"
            + "7 2 Blackjack\n"
            + "7 3 Rifle\n"
            + "7 3 Bowie Knife\n"
            + "8 5 C4\n"
            + "8 5 Bloodsport\n"
            + "8 5 Polka-dots\n"
            + "8 6 Rats";

    return new BufferedReader(new StringReader(data));
  }

  /**
   * Bad test data to check proper world building.
   *
   * @return Bad world data.
   */
  public Readable noPetName() {
    String data = "40 40 Corto Maltese\n"
            + "75 Starro The Conqueror\n"
            + "\n"
            + "9\n"
            + " 9  2 35  5 Beach Head One\n"
            + " 2  5  8 11 Beach Head Two\n"
            + "10 20 26 23 Town\n"
            + "16  6 30 13 Forest\n"
            + "19 14 25 16 Insurgent Camp\n"
            + "16 24 18 26 Road\n"
            + "14 -27 24 30 Palace\n"
            + "31 34 38 38 Guard House\n"
            + "10 33 30 39 Jotunheim\n"
            + "25\n"
            + "0 5 Helicopter\n"
            + "0 3 Mongal\n"
            + "0 1 Detachable Arms\n"
            + "0 2 Boomerang\n"
            + "1 3 Pistol\n"
            + "1 1 Ocean Water\n"
            + "1 1 Conch Shell\n"
            + "2 2 Beer bottle\n"
            + "2 2 Butterfly Knife\n"
            + "2 1 Trash Can\n"
            + "3 1 Small Stick\n"
            + "4 2 Blowgun\n"
            + "4 2 Sling Shot\n"
            + "4 5 Nanaway\n"
            + "5 4 Armored Truck\n"
            + "6 5 Machine Guns\n"
            + "6 4 Antique Pistol\n"
            + "6 3 Javelin\n"
            + "7 2 Blackjack\n"
            + "7 3 Rifle\n"
            + "7 3 Bowie Knife\n"
            + "8 5 C4\n"
            + "8 5 Bloodsport\n"
            + "8 5 Polka-dots\n"
            + "8 6 Rats";

    return new BufferedReader(new StringReader(data));
  }
}
