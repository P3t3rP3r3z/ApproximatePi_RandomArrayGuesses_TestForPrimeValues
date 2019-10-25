import kotlin.random.Random

data class Dart(var x: Double?, var y: Double?){

    fun throwDart(){
        this.x = Random.nextDouble(-1.0, 1.0)
        this.y = Random.nextDouble(-1.0, 1.0)
        //println("dart position is (${x},${y})")
    }
}