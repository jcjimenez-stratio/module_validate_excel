import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types._
import org.apache.log4j._
import com.crealytics.spark.excel._

object excel {

  import org.apache.spark._
  import org.apache.spark.sql.SQLContext
  import org.apache.spark.sql._

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder
      .master("local")
      .appName("excel")
      .getOrCreate()

    //val sc = SQLContext(spark)

    Logger.getLogger("org").setLevel(Level.ERROR)

    val excelSchema = StructType(Array(
      StructField("Proyecto/Equipo", StringType, nullable = false),
      StructField("Sprint", IntegerType, nullable = false),
      StructField("Nombre tabla origen", StringType, nullable = false),
      StructField("Nombre campo origen", StringType, nullable = false),
      StructField("Nombre tabla destino", StringType, nullable = false),
      StructField("Nombre campo destino", StringType, nullable = false),
      StructField("Descripcion", StringType, nullable = false),
      StructField("Tipo del Campo", StringType, nullable = false),
      StructField("Datos identificativos de personas ", StringType, nullable = false),
      StructField("Información Personal", StringType, nullable = false),
      StructField("Información especialmente sensible", StringType, nullable = false)
    ))

//    val df = spark.read
//      .format("com.crealytics.spark.excel")
//      .option("useHeader", "true") // Required
//      .option("sheetName", "FichaTecnica_Tablas")
//      .schema(excelSchema) // Optional, default: Either inferred schema, or all columns are Strings
//      .load("PLANTILLA1_BIGDATA_INFORMACION.xlsx")

    //val df = spark.read.option("sheetName", "FichaTecnica_Tablas").load("PLANTILLA1_BIGDATA_INFORMACION.xlsx")

    spark.read.excel()


    val df = spark.read.format("com.crealytics.spark.excel")
      .option("location", "PLANTILLA1_BIGDATA_INFORMACION.xlsx")
      .option("useHeader", "true")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .option("addColorColumns", "False")
      .option("sheetName", "FichaTecnica_Tablas")
      .load()

 // df.show(10)

  }




}
