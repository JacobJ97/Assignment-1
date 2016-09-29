/**
 * Created by outba on 9/2/2016.
 */
public class STcard {
    private int id;
    private int i;
    public static int x = 0;
    public static int y = -54;
    private String[] cardContents = new String[6];
    private String[] cardName = {"Quartz", "Plagioclase", "Orthoclase", "Biotite", "Muscovite", "Hornblende", "Actinolite",
            "Glaucophane", "Olivine", "Garnet", "Titanite", "Zircon", "Augite", "Orthopyroxene", "Chlorite",
            "Antigorite", "Talc", "Kaolinite", "Andalusite", "Kyanite", "Sillimanite", "Staurolite", "Epidote",
            "Tourmaline", "Topaz", "Beryl", "Pyrite", "Pyrrhotite", "Chalcopyrite", "Galena", "Sphalerite",
            "Molybdenite", "Gold", "Diamond", "Graphite", "Halite", "Fluorite", "Gypsum", "Barite", "Apatite", "Monazite",
            "Calcite", "Dolomite", "Magnesite", "Siderite", "Magnetite", "Hematite", "Chromite", "Ilmenite", "Rutile",
            "Corundum", "Cassiterite", "Gibbsite", "Goethite"};

    private String[] cardHardness = {"7", "6-6.5", "6-6.5", "2.5-3", "2.5-3", "5-6", "5-6", "6", "6.5-7", "6-7.5", "5-5.5", "7.5",
            "5.5-6.5", "5-6", "2-3", "3.5-4", "1", "1.5-2.5", "6.5-7", "5.5-7", "6.5-7.5", "7", "6-6.5", "7-7.5", "8",
            "7.5-8", "6-6.5", "3.5-4.5", "3.5-4", "2.5", "3.5-4", "1-1.5", "2.5-3", "10", "1-2", "2.5", "4", "2",
            "3-3.5", "5", "5", "3", "3.5-4", "4", "4-4.5", "5.5-6", "5-6", "5.5", "5-6", "6-6.5", "9", "6-7", "2.5-3.5",
            "5-5.5"};

    private String[] cardGravity = {"2.65", "2.6-2.8", "2.5-2.6", "2.7-3.3", "2.8-2.9", "3.0-3.5", "3.0-3.5", "3.0-3.2",
            "3.2-4.4", "3.5-4.3", "3.4-3.6", "4.6-4.7", "3.2-3.6", "3.2-3.9", "2.6-3.3", "2.6", "2.6-2.8", "2.6-2.7",
            "3.15", "3.5-3.7", "3.25", "3.7-3.8", "3.2-3.5", "3-3.2", "3.5-3.6", "2.6-2.9", "5.0", "4.6", "4.1-4.3",
            "7.5-7.6", "3.9-4.1", "4.7", "19.3", "3.5", "2.2", "2.2", "3.2", "2.3", "4.5", "3.1-3.2", "5-5.3", "2.7",
            "2.9", "3.0", "4.0", "5.2", "5.3", "4.5-5.1", "4.7-4.8", "4.3", "4.0", "6.9-7.1", "2.4", "4.3"};

    private String[] cardCleagage = {"poor/none", "1 perfect, 1 good", "1 perfect, 1 good", "1 perfect", "1 perfect", "2 good", "2 good",
            "2 good", "2 poor", "none", "3 good", "2 poor", "2 good", "2 good", "1 perfect", "1 perfect", "1 perfect",
            "1 perfect", "2 good", "1 perfect, 1 good", "1 perfect, 1 good", "1 good", "1 perfect", "2 poor",
            "1 perfect", "1 poor", "2 poor", "none", "2 poor", "3 perfect", "6 perfect", "1 perfect", "none",
            "4 perfect", "1 perfect", "3 perfect", "4 perfect", "1 perfect, 2 good", "2 perfect, 1 good", "2 poor",
            "1 good, 1 poor", "3 perfect", "3 perfect", "3 perfect", "3 perfect", "none", "none", "none", "none",
            "2 good", "none", "1 good, 1 poor", "1 perfect", "1 perfect, 1 good"};

    private String[] cardCrustalAbundance = {"high", "very high", "high", "moderate", "moderate", "moderate", "low", "low",
            "high", "moderate", "low", "trace", "high", "high", "moderate", "low", "low", "moderate", "low", "trace",
            "low", "trace", "moderate", "trace", "ultratrace", "trace", "low", "low", "low", "trace", "trace", "trace",
            "ultratrace", "ultratrace", "trace", "trace", "trace", "trace", "trace", "low", "trace", "moderate", "low",
            "low", "trace", "moderate", "trace", "low", "low", "low", "trace", "trace", "low", "moderate"};

    private String[] cardEconomicValue = {"moderate", "moderate", "moderate", "low", "moderate", "trivial", "low", "low", "low",
            "moderate", "low", "moderate", "trivial", "trivial", "low", "low", "moderate", "high", "moderate",
            "moderate", "low", "low", "trivial", "moderate", "low", "moderate", "moderate", "moderate", "very high",
            "high", "high", "high", "I'm rich!", "I'm rich!", "moderate", "moderate", "moderate", "high", "moderate",
            "high", "moderate", "high", "low", "moderate", "moderate", "very high", "high", "high", "moderate", "high",
            "moderate", "high", "high", "moderate"};

    private String[] cardNameSuperTrump = {"The Miner", "The Petrologist", "The Gemmologist",
            "The Mineralogist", "The Geophysicist", "The Geologist"};

    private String[] superTrumpCardDesc = {"Change trump category to 'Economic Value'",
            "Change trump category to 'Crustal Abundance'", "Change trump category to 'Hardness'",
            "Change trump category to 'Cleavage", "Change trump category to 'Specific Gravity' or throw 'Magnetite'",
            "Change trump category to category of your choice"};


    public STcard(int id) {
        this.id = id;
        if (x <= 53) {
            cardContents[0] = cardName[x];
            cardContents[1] = cardHardness[x];
            cardContents[2] = cardGravity[x];
            cardContents[3] = cardCleagage[x];
            cardContents[4] = cardCrustalAbundance[x];
            cardContents[5] = cardEconomicValue[x];
        }
        if (y > -1) {
            cardContents[0] = cardNameSuperTrump[y];
            cardContents[1] = superTrumpCardDesc[y];
        }
    }

    public String toString() {
        if (id <= 54) {
            return "\n" + ("Card ID: " + id +
                    " | Name: " + cardContents[0] +
                    " | Hardness: " + cardContents[1] +
                    " | Gravity: " + cardContents[2] +
                    " | Cleavage: " + cardContents[3] +
                    " | Crustal Abundance: " + cardContents[4] +
                    " | Economic Value: " + cardContents[5]);
        }
        else {
            return "\n" + ("Card ID: " + id +
                    " | SUPERTRUMP CARD " +
                    " | Name: " + cardContents[0] +
                    " | Action: " + cardContents[1]);
        }
    }
}
