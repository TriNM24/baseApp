package android.com.baseapp.adapter.itemObject


object ExpandableListDataItems {
    fun getData(): LinkedHashMap<String, List<String>> {
        val expandableDetailList = LinkedHashMap<String, List<String>>()

        // As we are populating List of fruits, vegetables and nuts, using them here
        // We can modify them as per our choice.
        // And also choice of fruits/vegetables/nuts can be changed

        // As we are populating List of fruits, vegetables and nuts, using them here
        // We can modify them as per our choice.
        // And also choice of fruits/vegetables/nuts can be changed
        val fruits: MutableList<String> = ArrayList()
        fruits.add("Apple")
        fruits.add("Orange")
        fruits.add("Guava")
        fruits.add("Papaya")
        fruits.add("Pineapple")

        val vegetables: MutableList<String> = ArrayList()
        vegetables.add("Tomato")
        vegetables.add("Potato")
        vegetables.add("Carrot")
        vegetables.add("Cabbage")
        vegetables.add("Cauliflower")

        val nuts: MutableList<String> = ArrayList()
        nuts.add("Cashews")
        nuts.add("Badam")
        nuts.add("Pista")
        nuts.add("Raisin1")
        nuts.add("Walnut2")
        nuts.add("Walnut3")
        nuts.add("Walnut4")
        nuts.add("Walnut5")
        nuts.add("Walnut6")
        nuts.add("Walnut7")
        nuts.add("Walnut8")
        nuts.add("Walnut9")
        nuts.add("Walnut10")
        nuts.add("Walnut11")
        nuts.add("Walnut12")
        nuts.add("Walnut13")
        nuts.add("Walnut14")
        nuts.add("Walnut15")
        nuts.add("Walnut16")
        nuts.add("Walnut17")
        nuts.add("Walnut18")
        nuts.add("Walnut19")
        nuts.add("Walnut20")

        // Fruits are grouped under Fruits Items. Similarly the rest two are under
        // Vegetable Items and Nuts Items respectively.
        // i.e. expandableDetailList object is used to map the group header strings to
        // their respective children using an ArrayList of Strings.

        // Fruits are grouped under Fruits Items. Similarly the rest two are under
        // Vegetable Items and Nuts Items respectively.
        // i.e. expandableDetailList object is used to map the group header strings to
        // their respective children using an ArrayList of Strings.
        expandableDetailList["Fruits Items"] = fruits
        expandableDetailList["Vegetable Items"] = vegetables
        expandableDetailList["Nuts Items"] = nuts
        expandableDetailList["Items 1"] = nuts
        expandableDetailList["Items 2"] = nuts
        expandableDetailList["Items 3"] = nuts
        expandableDetailList["Items 4"] = nuts
        expandableDetailList["Items 5"] = nuts
        expandableDetailList["Items 6"] = nuts
        expandableDetailList["Items 7"] = nuts
        expandableDetailList["Items 8"] = nuts
        expandableDetailList["Items 9"] = nuts
        expandableDetailList["Items 10"] = nuts

        return expandableDetailList
    }
}