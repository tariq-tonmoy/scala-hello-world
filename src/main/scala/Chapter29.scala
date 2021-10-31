import recipe._

object Chapter29 {

    def brwoseFoods = {
        val regularBrowser = new RecipeBrowser with RegularRecipeDb
        val studentBrowser = new RecipeBrowser with StudentRecipeDb

        regularBrowser.findAllCategories.foreach(println)
        println
        regularBrowser.findAllFood.foreach(x => {
            println(x)
            regularBrowser.findRecipesByFood(x).foreach(println)
            println
        })

        val frozenFood = new ExoticFoods {}.FrozenFood
        println(frozenFood)

        studentBrowser.findAllRecepies.foreach(println)

    }

    def browseComplexFoods = {
        val complexRecipeBrowser = new ComplexRecipeBrowser with RegularRecipeDb with RecipeMixer with ComplexMixer {
            override val recipeMixer: RecipeMixer = new RecipeMixer with StudentRecipeDb with ComplexMixer {
                override val recipeMixer: RecipeMixer = new RecipeMixer with StudentRecipeDb with ComplexMixer {
                    override val recipeMixer: RecipeMixer = new RecipeMixer with RegularRecipeDb with ComplexMixer {
                        override val recipeMixer: RecipeMixer = null
                    }
                }
            }
        }

        println("\n\n\n")
        complexRecipeBrowser.findAllFood.foreach(println)
        println(complexRecipeBrowser.findAllFood.length)

    }

    @main def chapterTwentyNineTests = {
        brwoseFoods
        browseComplexFoods
    }
}
