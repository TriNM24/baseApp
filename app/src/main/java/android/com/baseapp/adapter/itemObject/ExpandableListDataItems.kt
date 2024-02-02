package android.com.baseapp.adapter.itemObject


object ExpandableListDataItems {
    fun getData(): HashMap<String, List<String>> {
        val expandableDetailList = HashMap<String, List<String>>()

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
        nuts.add("Raisin")
        nuts.add("Walnut")

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

        return expandableDetailList
    }
}