package hs.kr.equus.user.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct

@Configuration
class LicenseConfig(
    @Value("\${pass.license_file_url}")
    val licenseFileURl: String
) {
    @PostConstruct
    fun initialize() {
        try {
            URL(licenseFileURl).openStream()
                .use { inputStream ->
                    Files.copy(inputStream, Paths.get(PATH))
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val PATH = "./V61290000000_IDS_01_PROD_AES_license.dat"
    }
}
