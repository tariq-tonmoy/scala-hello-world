package implicits

import animals.FarmAnimalFood
import implicits.helpers.{AnimalsProvider, RationalNumberProvider}
import implicits.helpers.AnimalsProvider.convertAnimalToActionHelper
import animalsounds.AnimalSound.*
import implicits.helpers.StudentProvider
import implicits.helpers.StudentProvider._
import implicits.studentgrades.StudentGrade
import implicits.studentgrades.StudentGrade.*
import students._

object ImplicitTypeConversion {

    def testRationals = {
        val rationals_11_10 = RationalNumberProvider.getRarionalString_1_2 + RationalNumberProvider.getRarional3_5
        println(rationals_11_10)

        val rationals_12_1 = RationalNumberProvider.getRarional_9 + RationalNumberProvider.getRarionalString_3
        println(rationals_12_1)

        val rationals_7_2 = RationalNumberProvider.getRarionalString_1_2 + RationalNumberProvider.getRarionalString_3
        println(rationals_7_2)
    }

    def testAnimalSounds = {
        val cow = AnimalsProvider.getCow("Molly")
        val cowSound = cow.generateSound
        println(cowSound)
        val goat = AnimalsProvider.getGoat("Dolly")
        val goatSound = goat.generateSound
        println(goatSound)

        val bear = AnimalsProvider.getBear("Beary")
        //        Runtime Exception: generateSound not implemented for bear
        //        val bearSound = bear.generateSound
        //        println(bearSound)
        println(bear.name)
    }

    def testAnimalFoods = {
        val cow = AnimalsProvider.getCow("Molly")
        val grass = AnimalsProvider.getGrass
        val fish = AnimalsProvider.getFish
        val goat = AnimalsProvider.getGoat("Dolly")
        val bear = AnimalsProvider.getBear("Beary")

        cow x grass
        cow x fish

        goat x grass
        goat x fish

        bear x grass
        bear x fish
    }

    def testStudents = {
        println("\n\n")

        val boringMath = StudentProvider.getBoringMaths
        val moderateEcon = StudentProvider.getModerateEconomics
        val interestingEnglish = StudentProvider.getInterestingEnglish

        val scienceStudent: Student[Science] = StudentProvider.getScienceStudent
        val commStudent = StudentProvider.getCommerceStudent
        val artsStudent = StudentProvider.getArtsStudent

        println(scienceStudent.takes(boringMath))
        println(scienceStudent.takes(interestingEnglish))
        //        println(scienceStudent.takes(moderateEcon))

        println(commStudent.takes(boringMath))
        //println(commStudent.takes(interestingEnglish))
        println(commStudent.takes(moderateEcon))

        //println(artsStudent.takes(boringMath))
        println(artsStudent.takes(interestingEnglish))
        println(artsStudent.takes(moderateEcon))

        println()

        println(scienceStudent.grade(boringMath))
        println(scienceStudent.grade(interestingEnglish))
        //        println(scienceStudent.grade(moderateEcon))

        println(commStudent.grade(boringMath))
        //        println(commStudent.grade(interestingEnglish))
        println(commStudent.grade(moderateEcon))


        //        println(artsStudent.grade(boringMath))
        println(artsStudent.grade(interestingEnglish))
        println(artsStudent.grade(moderateEcon))

        artsStudent.test(interestingEnglish)
        //        artsStudent.test(moderateEcon)

        println()

        Console println artsStudent --> interestingEnglish
        Console println artsStudent --> moderateEcon
        //        Console println artsStudent --> boringMath

        Console println scienceStudent --> interestingEnglish
        //        Console println scienceStudent --> moderateEcon
        Console println scienceStudent --> boringMath

        //        Console println commStudent --> interestingEnglish
        Console println commStudent --> moderateEcon
        Console println commStudent --> boringMath

        println()
    }
}
