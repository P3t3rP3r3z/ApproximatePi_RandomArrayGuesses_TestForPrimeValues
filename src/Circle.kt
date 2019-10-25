
class Circle(var center: Int, var radius: Double){

    fun getArea(radius:Double):Double{

        var area = radius*radius
        area = area*kotlin.math.PI

        return area
    }
}