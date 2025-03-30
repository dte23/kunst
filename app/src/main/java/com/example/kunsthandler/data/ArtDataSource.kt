package com.example.kunsthandler.data

import com.example.kunsthandler.R

object ArtDataSource {
    val artists = listOf(
        Artist(1, "Oskar", "Hansen"),
        Artist(2, "Ingrid", "Olsen"),
        Artist(3, "Anders", "Bergström"),
        Artist(4, "Maja", "Johansen"),
        Artist(5, "Erik", "Nilsen"),
        Artist(6, "Frida", "Larsen"),
        Artist(7, "Magnus", "Pedersen")
    )

    val photos = listOf(
        Photo(
            id = 1,
            title = "Nature Scene",
            imageResId = R.drawable.nature_1,
            artist = artists[0],
            category = Category.NATURE,
            price = 1200f
        ),
        Photo(
            id = 2,
            title = "Food Display",
            imageResId = R.drawable.food_1,
            artist = artists[1],
            category = Category.FOOD,
            price = 800f
        ),
        Photo(
            id = 3,
            title = "Sports Action",
            imageResId = R.drawable.sport_1,
            artist = artists[2],
            category = Category.SPORT,
            price = 1500f
        ),
        Photo(
            id = 4,
            title = "Sweet Donut",
            imageResId = R.drawable.donut,
            artist = artists[1],
            category = Category.FOOD,
            price = 950f
        ),
        Photo(
            id = 5,
            title = "Ice Cream Sandwich",
            imageResId = R.drawable.icecreamsandwich,
            artist = artists[0],
            category = Category.FOOD,
            price = 850f
        ),
        Photo(
            id = 6,
            title = "Cupcake Delight",
            imageResId = R.drawable.cupcake,
            artist = artists[2],
            category = Category.FOOD,
            price = 750f
        ),
        Photo(
            id = 7,
            title = "Superhero Action",
            imageResId = R.drawable.android_superhero1,
            artist = artists[2],
            category = Category.SPORT,
            price = 1600f
        ),
        Photo(
            id = 8,
            title = "Hero Jump",
            imageResId = R.drawable.android_superhero2,
            artist = artists[0],
            category = Category.SPORT,
            price = 1400f
        ),
        Photo(
            id = 9,
            title = "Mountain View",
            imageResId = R.drawable.geology,
            artist = artists[1],
            category = Category.NATURE,
            price = 1300f
        ),
        Photo(
            id = 10,
            title = "Forest Life",
            imageResId = R.drawable.ecology,
            artist = artists[2],
            category = Category.NATURE,
            price = 1100f
        ),
        Photo(
            id = 11,
            title = "Honey Patterns",
            imageResId = R.drawable.honeycomb,
            artist = artists[3],
            category = Category.NATURE,
            price = 1250f
        ),
        Photo(
            id = 12,
            title = "Android Hero",
            imageResId = R.drawable.android_superhero6,
            artist = artists[4],
            category = Category.SPORT,
            price = 1450f
        ),
        Photo(
            id = 13,
            title = "Mountain Sunset",
            imageResId = R.drawable.image1,
            artist = artists[5],
            category = Category.NATURE,
            price = 1350f
        ),
        Photo(
            id = 14,
            title = "Urban Landscape",
            imageResId = R.drawable.image2,
            artist = artists[6],
            category = Category.NATURE,
            price = 1700f
        ),
        Photo(
            id = 15,
            title = "Sweet Delight",
            imageResId = R.drawable.froyo,
            artist = artists[3],
            category = Category.FOOD,
            price = 980f
        ),
        Photo(
            id = 16,
            title = "Dynamic Motion",
            imageResId = R.drawable.android_superhero3,
            artist = artists[5],
            category = Category.SPORT,
            price = 1550f
        ),
        Photo(
            id = 17,
            title = "Ecological Patterns",
            imageResId = R.drawable.image3,
            artist = artists[4],
            category = Category.NATURE,
            price = 1250f
        ),
        Photo(
            id = 18,
            title = "Culinary Art",
            imageResId = R.drawable.culinary,
            artist = artists[6],
            category = Category.FOOD,
            price = 900f
        ),
        Photo(
            id = 19,
            title = "Hero in Motion",
            imageResId = R.drawable.android_superhero4,
            artist = artists[3],
            category = Category.SPORT,
            price = 1650f
        ),
        Photo(
            id = 20,
            title = "Sweet Temptation",
            imageResId = R.drawable.gingerbread,
            artist = artists[4],
            category = Category.FOOD,
            price = 870f
        ),
        Photo(
            id = 21,
            title = "Bølgeformasjoner",
            imageResId = R.drawable.image4,
            artist = artists[6],
            category = Category.NATURE,
            price = 1800f
        ),
        Photo(
            id = 22,
            title = "Coastal Sunrise",
            imageResId = R.drawable.image5,
            artist = artists[0],
            category = Category.NATURE,
            price = 1650f
        ),
        Photo(
            id = 23,
            title = "Midnight Forest",
            imageResId = R.drawable.image6,
            artist = artists[2],
            category = Category.NATURE,
            price = 1750f
        ),
        Photo(
            id = 24,
            title = "Seaside Moment",
            imageResId = R.drawable.image7,
            artist = artists[5],
            category = Category.NATURE,
            price = 1850f
        ),
        Photo(
            id = 25,
            title = "Urban Travel",
            imageResId = R.drawable.image8,
            artist = artists[1],
            category = Category.NATURE,
            price = 1920f
        ),
        Photo(
            id = 26,
            title = "Beach Sunset",
            imageResId = R.drawable.image9,
            artist = artists[4],
            category = Category.NATURE,
            price = 2100f
        ),
        Photo(
            id = 27,
            title = "Mountain Adventure",
            imageResId = R.drawable.image10,
            artist = artists[3],
            category = Category.NATURE,
            price = 2250f
        ),
        Photo(
            id = 28,
            title = "Marshmallow Treat",
            imageResId = R.drawable.marshmallow,
            artist = artists[2],
            category = Category.FOOD,
            price = 950f
        ),
        Photo(
            id = 29,
            title = "Nougat Delight",
            imageResId = R.drawable.nougat,
            artist = artists[5],
            category = Category.FOOD,
            price = 1050f
        ),
        Photo(
            id = 30,
            title = "Oreo Dream",
            imageResId = R.drawable.oreo,
            artist = artists[0],
            category = Category.FOOD,
            price = 1150f
        ),
        Photo(
            id = 31,
            title = "Lollipop Fantasy",
            imageResId = R.drawable.lollipop,
            artist = artists[6],
            category = Category.FOOD,
            price = 1250f
        ),
        Photo(
            id = 32,
            title = "KitKat Creation",
            imageResId = R.drawable.kitkat,
            artist = artists[1],
            category = Category.FOOD,
            price = 1350f
        ),
        Photo(
            id = 33,
            title = "Jelly Bean Joy",
            imageResId = R.drawable.jellybean,
            artist = artists[4],
            category = Category.FOOD,
            price = 1050f
        ),
        Photo(
            id = 34,
            title = "Eclair Elegance",
            imageResId = R.drawable.eclair,
            artist = artists[3],
            category = Category.FOOD,
            price = 1150f
        ),
        Photo(
            id = 35,
            title = "Hero Stance",
            imageResId = R.drawable.android_superhero5,
            artist = artists[1],
            category = Category.SPORT,
            price = 1700f
        ),
        Photo(
            id = 36,
            title = "Design Elements",
            imageResId = R.drawable.design,
            artist = artists[4],
            category = Category.NATURE,
            price = 1950f
        ),
        Photo(
            id = 37,
            title = "Artistic Drawing",
            imageResId = R.drawable.drawing,
            artist = artists[6],
            category = Category.NATURE,
            price = 2050f
        ),
        Photo(
            id = 38,
            title = "Fashion Forward",
            imageResId = R.drawable.fashion,
            artist = artists[2],
            category = Category.NATURE,
            price = 2150f
        ),
        Photo(
            id = 39,
            title = "Film Composition",
            imageResId = R.drawable.film,
            artist = artists[5],
            category = Category.NATURE,
            price = 1950f
        ),
        Photo(
            id = 40,
            title = "Gaming World",
            imageResId = R.drawable.gaming,
            artist = artists[3],
            category = Category.SPORT,
            price = 1850f
        ),
        Photo(
            id = 41,
            title = "Lifestyle",
            imageResId = R.drawable.lifestyle,
            artist = artists[0],
            category = Category.NATURE,
            price = 1750f
        ),
        Photo(
            id = 42,
            title = "Painting Masterpiece",
            imageResId = R.drawable.painting,
            artist = artists[1],
            category = Category.NATURE,
            price = 2200f
        )
    )

    fun photosByCategory(category: Category): List<Photo> {
        return photos.filter { it.category == category }
    }

    fun photosByArtist(artist: Artist): List<Photo> {
        return photos.filter { it.artist.id == artist.id }
    }

    fun photoById(id: Long): Photo? {
        return photos.find { it.id == id }
    }
} 