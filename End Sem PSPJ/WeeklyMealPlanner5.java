
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WeeklyMealPlanner5 {

    static Scanner sc = new Scanner(System.in);

    static String[] days = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    };

    static String[] vegBreakfast = {
        "Paneer Sandwich", "Moong Dal Chilla", "Sprouts Salad",
        "Oats with Milk", "Greek Yogurt with Nuts",
        "Peanut Butter Bread", "Besan Chilla", "Paneer Paratha",
        "Tofu Scramble", "Milk and Banana", "Boiled Chickpeas",
        "Soya Chunk Sandwich", "Ragi Dosa with Sambar",
        "Sprouted Moong Chaat", "Cottage Cheese Toast",
        "Almond Oatmeal", "Mixed Bean Salad", "Peanut Butter Oats",
        "Paneer Bhurji", "Milk with Nuts"
    };

    static String[] nonVegBreakfast = {
        "Boiled Eggs", "Bread Omelette", "Egg Bhurji",
        "Scrambled Eggs", "Egg Dosa", "Chicken Sandwich",
        "Chicken Wrap", "Egg Sandwich", "Omelette with Toast",
        "Tuna Sandwich", "Chicken Sausage", "Egg Paratha",
        "Chicken Keema Toast", "Egg Fried Rice",
        "Chicken and Egg Wrap"
    };

    static String[] vegLunch = {
        "Paneer Biryani", "Rajma Rice", "Dal with Rice",
        "Chickpea Salad", "Paneer Curry with Chapati",
        "Tofu Rice Bowl", "Soya Chunk Curry with Rice",
        "Sprouts Pulao", "Dal Khichdi", "Paneer Thali",
        "Chole with Chapati", "Mixed Bean Rice Bowl",
        "Palak Paneer with Rice", "Moong Dal with Rice",
        "Soya Chunk Biryani", "Paneer Fried Rice",
        "Chickpea Curry with Rice", "Tofu Curry with Chapati",
        "Mixed Dal with Chapati", "Paneer Pulao"
    };

    static String[] nonVegLunch = {
        "Chicken Biryani", "Grilled Chicken with Rice",
        "Chicken Curry with Chapati", "Fish Curry with Rice",
        "Egg Curry with Rice", "Chicken Breast with Rice",
        "Fish Fry with Rice", "Chicken Pulao", "Prawn Biryani",
        "Egg Biryani", "Chicken Tikka Rice", "Chicken Fried Rice",
        "Grilled Fish with Vegetables", "Chicken Keema Rice",
        "Chicken Thali", "Egg Bhurji with Chapati",
        "Chicken Curry Rice Bowl", "Prawn Curry with Rice",
        "Fish Biryani", "Chicken Wrap"
    };

    static String[] vegDinner = {
        "Paneer with Chapati", "Tofu Stir-Fry",
        "Dal with Chapati", "Chickpea Curry", "Paneer Bhurji",
        "Soya Chunk Curry", "Lentil Soup with Bread",
        "Moong Dal Khichdi", "Greek Yogurt with Sprouts",
        "Mixed Bean Salad", "Paneer Tikka with Chapati",
        "Tofu Rice Bowl", "Rajma with Chapati", "Palak Paneer",
        "Soya Chunk Pulao", "Chickpea Salad Bowl",
        "Mixed Dal Khichdi", "Paneer Curry with Rice",
        "Sprouts Chaat", "Besan Chilla with Curd"
    };

    static String[] nonVegDinner = {
        "Grilled Chicken with Vegetables",
        "Chicken Curry with Chapati", "Egg Curry with Chapati",
        "Fish with Rice", "Chicken Soup", "Chicken Stir-Fry",
        "Egg Bhurji with Chapati", "Grilled Fish with Vegetables",
        "Chicken Salad", "Prawn Curry with Rice",
        "Chicken Tikka with Chapati", "Boiled Eggs with Toast",
        "Chicken Pulao", "Fish Curry with Chapati",
        "Chicken Keema with Rice", "Egg Fried Rice",
        "Grilled Chicken Rice Bowl", "Chicken Pasta",
        "Fish Biryani", "Chicken and Vegetable Soup"
    };

    static Map<String, Double> groceryList = new TreeMap<>();
    static Map<String, Double> prices = new HashMap<>();

    static String padRight(String text, int width) {
        if (text.length() > width) {
            return text.substring(0, width - 3) + "...";
        }
        return String.format("%-" + width + "s", text);
    }

    static ArrayList<String> getMenu(
            String[] veg, String[] nonVeg, int preference) {

        ArrayList<String> menu = new ArrayList<>();

        if (preference == 1 || preference == 3) {
            for (String item : veg) {
                menu.add(item);
            }
        }

        if (preference == 2 || preference == 3) {
            for (String item : nonVeg) {
                menu.add(item);
            }
        }

        return menu;
    }

    static void printMenus(
            ArrayList<String> breakfast,
            ArrayList<String> lunch,
            ArrayList<String> dinner) {

        int max = Math.max(
            breakfast.size(),
            Math.max(lunch.size(), dinner.size())
        );

        System.out.println("\n========================== FOOD MENU ==========================");
        System.out.printf("%-42s %-42s %s%n",
                "BREAKFAST", "LUNCH", "DINNER");

        System.out.println(
            "-----------------------------------------------------------------------------------------------"
        );

        for (int i = 0; i < max; i++) {
            String b = i < breakfast.size()
                    ? (i + 1) + ". " + breakfast.get(i) : "";
            String l = i < lunch.size()
                    ? (i + 1) + ". " + lunch.get(i) : "";
            String d = i < dinner.size()
                    ? (i + 1) + ". " + dinner.get(i) : "";

            System.out.printf("%-42s %-42s %s%n", b, l, d);
        }

        System.out.println(
            "-----------------------------------------------------------------------------------------------"
        );
    }

    // Prompts separately for each meal and validates the choice.
    static int readChoice(int max, String meal) {
        while (true) {
            System.out.print(meal + " choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice >= 1 && choice <= max) {
                    return choice;
                }

                System.out.println(
                    "Invalid choice! Enter a number from 1 to " + max
                );
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
    }

    static void addItem(String item, double quantity) {
        groceryList.put(
            item,
            groceryList.getOrDefault(item, 0.0) + quantity
        );
    }

    static void initializePrices() {
        prices.put("Almonds/Nuts (kg)", 800.0);
        prices.put("Besan (kg)", 100.0);
        prices.put("Bread/Wheat Flour (kg)", 45.0);
        prices.put("Chicken (kg)", 250.0);
        prices.put("Chickpeas (kg)", 100.0);
        prices.put("Cooking Oil (litre)", 150.0);
        prices.put("Curd (kg)", 80.0);
        prices.put("Dal/Lentils (kg)", 150.0);
        prices.put("Eggs (pieces)", 8.0);
        prices.put("Fish (kg)", 300.0);
        prices.put("Fruits (kg)", 80.0);
        prices.put("Ghee (kg)", 600.0);
        prices.put("Green Chillies (kg)", 80.0);
        prices.put("Masala and Spices (kg)", 300.0);
        prices.put("Milk (litre)", 60.0);
        prices.put("Mint/Coriander (kg)", 100.0);
        prices.put("Mixed Beans (kg)", 150.0);
        prices.put("Onions (kg)", 40.0);
        prices.put("Oats (kg)", 120.0);
        prices.put("Paneer (kg)", 350.0);
        prices.put("Peanut Butter (kg)", 250.0);
        prices.put("Prawns (kg)", 400.0);
        prices.put("Rice (kg)", 60.0);
        prices.put("Soya Chunks (kg)", 120.0);
        prices.put("Tofu (kg)", 250.0);
        prices.put("Tomatoes (kg)", 40.0);
        prices.put("Vegetables/Greens (kg)", 50.0);
        prices.put("Yogurt (kg)", 100.0);
        prices.put("Dosa/Idli Batter (kg)", 60.0);
    }

    static void addIngredients(String meal, int people) {
        String m = meal.toLowerCase();

        if (m.contains("biryani")) {
            addItem("Rice (kg)", 0.12 * people);
            addItem("Onions (kg)", 0.04 * people);
            addItem("Tomatoes (kg)", 0.04 * people);
            addItem("Curd (kg)", 0.04 * people);
            addItem("Green Chillies (kg)", 0.005 * people);
            addItem("Mint/Coriander (kg)", 0.01 * people);
            addItem("Cooking Oil (litre)", 0.015 * people);
            addItem("Ghee (kg)", 0.005 * people);
            addItem("Masala and Spices (kg)", 0.01 * people);

            if (m.contains("paneer")) {
                addItem("Paneer (kg)", 0.10 * people);
            }
            if (m.contains("soya")) {
                addItem("Soya Chunks (kg)", 0.06 * people);
            }
            if (m.contains("chicken")) {
                addItem("Chicken (kg)", 0.15 * people);
            }
            if (m.contains("prawn")) {
                addItem("Prawns (kg)", 0.15 * people);
            }
            if (m.contains("fish")) {
                addItem("Fish (kg)", 0.15 * people);
            }
            if (m.contains("egg")) {
                addItem("Eggs (pieces)", 2.0 * people);
            }

            return;
        }

        if (m.contains("paneer") || m.contains("cottage cheese")) {
            addItem("Paneer (kg)", 0.10 * people);
        }
        if (m.contains("chicken")) {
            addItem("Chicken (kg)", 0.15 * people);
        }
        if (m.contains("fish") || m.contains("tuna")) {
            addItem("Fish (kg)", 0.15 * people);
        }
        if (m.contains("prawn")) {
            addItem("Prawns (kg)", 0.15 * people);
        }
        if (m.contains("egg") || m.contains("omelette")) {
            addItem("Eggs (pieces)", 2.0 * people);
        }

        if (m.contains("rice") || m.contains("pulao")
                || m.contains("khichdi") || m.contains("fried rice")) {
            addItem("Rice (kg)", 0.10 * people);
        }

        if (m.contains("chapati") || m.contains("paratha")
                || m.contains("bread") || m.contains("toast")
                || m.contains("sandwich") || m.contains("wrap")
                || m.contains("pasta")) {
            addItem("Bread/Wheat Flour (kg)", 0.08 * people);
        }

        if (m.contains("dal") || m.contains("lentil")
                || m.contains("sambar") || m.contains("chilla")) {
            addItem("Dal/Lentils (kg)", 0.06 * people);
        }

        if (m.contains("chickpea") || m.contains("chole")) {
            addItem("Chickpeas (kg)", 0.08 * people);
        }

        if (m.contains("bean") || m.contains("rajma")) {
            addItem("Mixed Beans (kg)", 0.08 * people);
        }

        if (m.contains("soya") || m.contains("soy")) {
            addItem("Soya Chunks (kg)", 0.06 * people);
        }

        if (m.contains("tofu")) {
            addItem("Tofu (kg)", 0.10 * people);
        }

        if (m.contains("milk")) {
            addItem("Milk (litre)", 0.20 * people);
        }

        if (m.contains("oat")) {
            addItem("Oats (kg)", 0.06 * people);
        }

        if (m.contains("yogurt") || m.contains("curd")) {
            addItem("Yogurt (kg)", 0.10 * people);
        }

        if (m.contains("banana")) {
            addItem("Fruits (kg)", 0.12 * people);
        }

        if (m.contains("nut") || m.contains("almond")) {
            addItem("Almonds/Nuts (kg)", 0.02 * people);
        }

        if (m.contains("vegetable") || m.contains("salad")
                || m.contains("sprout") || m.contains("palak")
                || m.contains("chilla") || m.contains("besan")
                || m.contains("stir-fry") || m.contains("soup")) {
            addItem("Vegetables/Greens (kg)", 0.10 * people);
        }

        if (m.contains("besan")) {
            addItem("Besan (kg)", 0.06 * people);
        }

        if (m.contains("dosa") || m.contains("idli")
                || m.contains("batter")) {
            addItem("Dosa/Idli Batter (kg)", 0.08 * people);
        }

        if (m.contains("peanut butter")) {
            addItem("Peanut Butter (kg)", 0.02 * people);
        }

        addItem("Onions (kg)", 0.03 * people);
        addItem("Cooking Oil (litre)", 0.01 * people);

        if (m.contains("curry") || m.contains("pulao")
                || m.contains("masala") || m.contains("paneer")
                || m.contains("chicken") || m.contains("fish")) {
            addItem("Tomatoes (kg)", 0.04 * people);
            addItem("Masala and Spices (kg)", 0.005 * people);
        }
    }

    static void mainMenu() {
        System.out.println("======================================");
        System.out.println("     HIGH-PROTEIN WEEKLY MEAL PLANNER");
        System.out.println("======================================");

        int people;

        while (true) {
            System.out.print("Enter number of people: ");

            if (sc.hasNextInt()) {
                people = sc.nextInt();

                if (people > 0) {
                    break;
                }
            } else {
                sc.next();
            }

            System.out.println("Enter a valid positive number.");
        }

        System.out.println("\nSelect Food Preference:");
        System.out.println("1. Vegetarian");
        System.out.println("2. Non-Vegetarian");
        System.out.println("3. Mixed");

        int preference;

        while (true) {
            System.out.print("Enter preference (1-3): ");

            if (sc.hasNextInt()) {
                preference = sc.nextInt();

                if (preference >= 1 && preference <= 3) {
                    break;
                }
            } else {
                sc.next();
            }

            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }

        ArrayList<String> breakfast =
            getMenu(vegBreakfast, nonVegBreakfast, preference);

        ArrayList<String> lunch =
            getMenu(vegLunch, nonVegLunch, preference);

        ArrayList<String> dinner =
            getMenu(vegDinner, nonVegDinner, preference);

        printMenus(breakfast, lunch, dinner);

        String[] selectedBreakfast = new String[7];
        String[] selectedLunch = new String[7];
        String[] selectedDinner = new String[7];

        // INPUT: ask separately for breakfast, lunch and dinner each day.
        for (int i = 0; i < 7; i++) {
            System.out.println("\n" + days[i] + ":");

            int b = readChoice(breakfast.size(), "Breakfast");
            int l = readChoice(lunch.size(), "Lunch");
            int d = readChoice(dinner.size(), "Dinner");

            selectedBreakfast[i] = breakfast.get(b - 1);
            selectedLunch[i] = lunch.get(l - 1);
            selectedDinner[i] = dinner.get(d - 1);
        }

        // WEEKLY MEAL PLAN OUTPUT: keep the table format.
        System.out.println("\n================ WEEKLY MEAL PLAN ===================");

        System.out.printf("%-15s | %-28s | %-28s | %-30s |%n",
                "Day", "Breakfast", "Lunch", "Dinner");

        System.out.println(
            "-----------------+------------------------------+------------------------------+--------------------------------"
        );

        for (int i = 0; i < 7; i++) {
            System.out.printf("%-15s | %-28s | %-28s | %-30s |%n",
                    days[i],
                    padRight(selectedBreakfast[i], 28),
                    padRight(selectedLunch[i], 28),
                    padRight(selectedDinner[i], 30));
        }

        System.out.println(
            "-----------------+------------------------------+------------------------------+--------------------------------"
        );

        // Generate the combined grocery list.
        groceryList.clear();
        prices.clear();
        initializePrices();

        for (int i = 0; i < 7; i++) {
            addIngredients(selectedBreakfast[i], people);
            addIngredients(selectedLunch[i], people);
            addIngredients(selectedDinner[i], people);
        }

        // GROCERY LIST OUTPUT: one combined table, no category headings.
        System.out.println("\n================ GROCERY LIST =======================");

        System.out.printf("%-3s | %-26s | %-14s | %-14s | %-14s |%n",
                "No", "Grocery Item", "Quantity",
                "Price/Unit", "Total Cost");

        System.out.println(
            "----+----------------------------+----------------+----------------+----------------"
        );

        double totalCost = 0.0;
        int itemNo = 1;

        for (Map.Entry<String, Double> entry : groceryList.entrySet()) {
            String item = entry.getKey();
            double quantity = entry.getValue();
            double price = prices.getOrDefault(item, 0.0);
            double cost = quantity * price;

            totalCost += cost;

            String unit = "kg";

            if (item.toLowerCase().contains("litre")) {
                unit = "litre";
            } else if (item.toLowerCase().contains("pieces")) {
                unit = "pieces";
            }

            System.out.printf(
                    "%-3d | %-26s | %.2f %-8s | Rs. %-10.1f | Rs. %-10.1f |%n",
                    itemNo++,
                    padRight(item, 26),
                    quantity,
                    unit,
                    price,
                    cost);
        }

        System.out.println(
            "----+----------------------------+----------------+----------------+----------------"
        );

        System.out.printf("TOTAL GROCERY COST: Rs. %.2f%n", totalCost);
        System.out.printf("COST PER PERSON:    Rs. %.2f%n",
                totalCost / people);

        System.out.println(
            "\nWeekly meal plan and grocery list generated successfully!"
        );
    }

    public static void main(String[] args) {
        mainMenu();
        sc.close();
    }
}
