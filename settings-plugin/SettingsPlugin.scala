import sbt._

object SettingsPlugin extends Plugin {

	val newTask = TaskKey[Unit]("new-task")
	val newSetting = SettingKey[String]("new-setting")

	val newSettings = Seq(
		newSetting := "test",
		newTask <<= newSetting map { str => println(str) }
	)

}