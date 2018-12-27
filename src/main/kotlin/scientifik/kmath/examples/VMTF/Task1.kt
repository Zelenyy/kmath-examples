package scientifik.kmath.examples.VMTF

import scientifik.kmath.operations.DoubleField
import kotlin.math.floor
import scientifik.kmath.structures.NDArrays
import scientifik.kmath.structures.NDElement

/* Based on Alexandr Kisilev's solution of VMTETF task
https://github.com/yl3dy/vmetf/blob/master/task1.py
*/

// Constants:
const val A = 0.5
const val X0 = 0.1
const val F1 = 0.0
const val F2 = 1.0
const val Q = 0.1
// size
const val L = 1.0
const val H = 0.002
// time
const val T = 1.0
const val TAU = 0.001

val GRID_X_SIZE = floor(L / H).toInt() + 1
val GRID_T_SIZE = floor(T / TAU).toInt() + 1
val X_VALUES = linspace(0.0..L to GRID_X_SIZE).first
val T_VALUES = linspace(0.0..T to GRID_T_SIZE).first



fun linspace(range : Pair<ClosedFloatingPointRange<Double>,Int>, endPoint: Boolean = false): Pair<NDElement<Double, DoubleField>, Double> {
    val div = if (endPoint) (range.second - 1) else range.second
    val delta = range.first.start - range.first.endInclusive
    if (range.second > 1){
        val step = delta/div
        if (step == 0.0){ error("Bad ranges: step = $step")}
        val result = NDArrays.real1DArray(range.second){
            if ( endPoint and (it == range.second - 1) ){ range.first.endInclusive}
            range.first.start + it*step
        }
        return  result to step
    }
    else{
        val step = Double.NaN
        return NDArrays.real1DArray(1){range.first.start} to step
    }

}


fun exactSolution(t : Double): NDElement<Double, DoubleField> {
    val x_n = floor((X0 + A *t)/ H).toInt()
    return NDArrays.real1DArray(GRID_X_SIZE){
        if (it<x_n) {
            F2
        }
        else {
            F1
        }
    }
}



/*
Solution of linear equation using simple methods.
 */
fun linearMethodsSimple(time: Double){

}

/*
Solution of linear equation using Lax-Wendroff methods.
 */
fun linearMethodsLaxWendroff(){

}

fun main(){
    val exact = T_VALUES.map { it.second to exactSolution(it.second) }


}