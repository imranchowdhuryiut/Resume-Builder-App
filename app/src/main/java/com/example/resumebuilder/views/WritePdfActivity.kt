package com.example.resumebuilder.views


import android.os.Bundle
import android.widget.Toast
import com.tejpratapsingh.pdfcreator.activity.PDFCreatorActivity
import com.tejpratapsingh.pdfcreator.utils.PDFUtil.PDFUtilListener
import com.tejpratapsingh.pdfcreator.views.PDFBody
import com.tejpratapsingh.pdfcreator.views.PDFFooterView
import com.tejpratapsingh.pdfcreator.views.PDFHeaderView
import java.io.File
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.navArgs
import com.example.resumebuilder.R
import com.tejpratapsingh.pdfcreator.activity.PDFViewerActivity
import com.tejpratapsingh.pdfcreator.views.basic.*
import java.net.URLConnection
import com.tejpratapsingh.pdfcreator.views.basic.PDFTextView

import com.tejpratapsingh.pdfcreator.views.PDFTableView
import com.tejpratapsingh.pdfcreator.views.PDFTableView.PDFTableRowView


class WritePdfActivity : PDFCreatorActivity() {

    private val args by navArgs<WritePdfActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = "Resume_${args.resume.name}"
            supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(ContextCompat.getColor(baseContext, R.color.purple_700))
            )
        }

        createPDF("Resume_${args.resume.name}", object : PDFUtilListener {
            override fun pdfGenerationSuccess(savedPDFFile: File) {
                Toast.makeText(baseContext, "PDF Created", Toast.LENGTH_SHORT).show()
            }

            override fun pdfGenerationFailure(exception: Exception) {
                Toast.makeText(baseContext, "PDF NOT Created", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getHeaderView(forPage: Int): PDFHeaderView {
        val headerView = PDFHeaderView(applicationContext)

        val horizontalView = PDFHorizontalView(applicationContext)

        horizontalView.addView(getProfilePicture())
        horizontalView.addView(getPersonalInfo())
        headerView.addView(horizontalView)

        val lineSeparatorView = PDFLineSeparatorView(applicationContext).setBackgroundColor(Color.BLACK)
        headerView.addView(lineSeparatorView)

        return headerView
    }

    private fun getPersonalInfo(): PDFVerticalView {
        val verticalView = PDFVerticalView(applicationContext)
        val name =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        name.setText(args.resume.name + " (${args.resume.specialization})")
        val info =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
        info.setText(args.resume.mobileNumber + "\n" + args.resume.emailAddress + "\n" + args.resume.address + "\n")
        verticalView.addView(name)
        verticalView.addView(info)
        return verticalView
    }

    private fun getProfilePicture(): PDFImageView {
        val imageView = PDFImageView(applicationContext)
        val imageLayoutParam = LinearLayout.LayoutParams(60, 60, 0f)
        imageView.setImageScale(ImageView.ScaleType.CENTER_INSIDE)
        imageView.setImageFile(File(args.resume.imagePath!!))
        imageLayoutParam.setMargins(0, 0, 10, 0)
        imageView.setLayout(imageLayoutParam)
        return imageView
    }

    override fun getBodyViews(): PDFBody {
        val body = PDFBody()
        body.addView(getCareerObjective())
        body.addView(getWorkExperiences())
        body.addView(getSkills())
        body.addView(getEducations())
        body.addView(getProjects())
        return body
    }

    private fun getProjects(): PDFVerticalView {
        val projects = args.resume.projects
        val objective = PDFVerticalView(baseContext)
        val objectivePrompt =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        objectivePrompt.setText("\nNotable Projects")
        val lineSeparatorView = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        val textInTable = arrayOf("Project Name", "Team Size", "Summary", "Used Technology", "Role")

        val tableHeader = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText(s)
            tableHeader.addToRow(pdfTextView)
        }
        objective.addView(objectivePrompt)
        objective.addView(lineSeparatorView)
        objective.addView(tableHeader)

        for (project in projects!!) {
            val tableRowView = PDFTableRowView(applicationContext)
            val projectName = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            projectName.setText("${project?.projectName}")
            tableRowView.addToRow(projectName)

            val teamSize = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            teamSize.setText("${project?.teamSize}")
            tableRowView.addToRow(teamSize)

            val summary = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            summary.setText("${project?.projectSummary}")
            tableRowView.addToRow(summary)

            val usedTechnology = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            usedTechnology.setText("${project?.technologyUsed}")
            tableRowView.addToRow(usedTechnology)

            val role = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            role.setText("${project?.role}")
            tableRowView.addToRow(role)

            objective.addView(tableRowView)
        }
        val lineSeparatorView2 = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        objective.addView(lineSeparatorView2)
        return objective
    }

    private fun getEducations(): PDFVerticalView {
        val educations = args.resume.educations
        val objective = PDFVerticalView(baseContext)
        val objectivePrompt =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        objectivePrompt.setText("\nEducations")
        val lineSeparatorView = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        val textInTable = arrayOf("Degree", "Passing year", "Grade")

        val tableHeader = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText(s)
            tableHeader.addToRow(pdfTextView)
        }
        objective.addView(objectivePrompt)
        objective.addView(lineSeparatorView)
        objective.addView(tableHeader)

        for (education in educations!!) {
            val tableRowView = PDFTableRowView(applicationContext)
            val degreeName = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            degreeName.setText("${education?.degree}")
            tableRowView.addToRow(degreeName)

            val passingYear = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            passingYear.setText("${education?.passingYear}")
            tableRowView.addToRow(passingYear)

            val grade = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            grade.setText("${education?.grade}")
            tableRowView.addToRow(grade)

            objective.addView(tableRowView)
        }
        val lineSeparatorView2 = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        objective.addView(lineSeparatorView2)
        return objective
    }

    private fun getSkills(): PDFVerticalView {
        val skillList = args.resume.skills
        val objective = PDFVerticalView(baseContext)
        val objectivePrompt =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        objectivePrompt.setText("\nSkills")
        val lineSeparatorView = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        val textInTable = arrayOf("Skill Name", "Rate")

        val tableHeader = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText(s)
            tableHeader.addToRow(pdfTextView)
        }
        objective.addView(objectivePrompt)
        objective.addView(lineSeparatorView)
        objective.addView(tableHeader)

        for (work in skillList!!) {
            val tableRowView = PDFTableRowView(applicationContext)
            val companyName = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            companyName.setText("${work?.skill}")
            tableRowView.addToRow(companyName)

            val duration = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            duration.setText("${work?.rate}")
            tableRowView.addToRow(duration)

            objective.addView(tableRowView)
        }
        val lineSeparatorView2 = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        objective.addView(lineSeparatorView2)
        return objective
    }

    private fun getCareerObjective(): PDFVerticalView {
        val objective = PDFVerticalView(baseContext)
        val objectivePrompt =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        objectivePrompt.setText("\nCareer Objective")
        val objectiveText =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
        val lineSeparatorView = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        val lineSeparatorView2 = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        objectiveText.setText(args.resume.objective + "\n")
        objective.addView(objectivePrompt)
        objective.addView(lineSeparatorView)
        objective.addView(objectiveText)
        objective.addView(lineSeparatorView2)
        return objective
    }

    private fun getWorkExperiences(): PDFView {
        val workList = args.resume.workExperiences
        var sum = 0
        workList?.forEach {
            sum += it?.duration ?: 0
        }
        val objective = PDFVerticalView(baseContext)
        val objectivePrompt =  PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.H3)
        objectivePrompt.setText("\nWork Experiences (${sum} years)")
        val lineSeparatorView = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        val textInTable = arrayOf("Company Name", "Duration")

        val tableHeader = PDFTableRowView(applicationContext)
        for (s in textInTable) {
            val pdfTextView = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.P)
            pdfTextView.setText(s)
            tableHeader.addToRow(pdfTextView)
        }
        objective.addView(objectivePrompt)
        objective.addView(lineSeparatorView)
        objective.addView(tableHeader)

        for (work in workList!!) {
            val tableRowView = PDFTableRowView(applicationContext)
            val companyName = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            companyName.setText("${work?.companyName}")
            tableRowView.addToRow(companyName)

            val duration = PDFTextView(applicationContext, PDFTextView.PDF_TEXT_SIZE.SMALL)
            duration.setText("${work?.duration} years")
            tableRowView.addToRow(duration)

            objective.addView(tableRowView)
        }
        val lineSeparatorView2 = PDFLineSeparatorView(baseContext).setBackgroundColor(Color.BLACK)
        objective.addView(lineSeparatorView2)
        return objective
    }

    override fun getFooterView(forPage: Int): PDFFooterView {
        return PDFFooterView(this.baseContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pdf_viewer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish();
        } else if (item.itemId == R.id.menuSharePdf) {
            val fileToShare = pdfFile;
            if (fileToShare == null || !fileToShare.exists()) {
                Toast.makeText(this, R.string.text_generated_file_error, Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }

            val intentShareFile = Intent(Intent.ACTION_SEND);

            val apkURI = FileProvider.getUriForFile(
                applicationContext,
                applicationContext.packageName + ".provider",
                fileToShare
            )
            intentShareFile.setDataAndType(
                apkURI,
                URLConnection.guessContentTypeFromName(
                    fileToShare.name
                )
            )
            intentShareFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentShareFile.putExtra(
                Intent.EXTRA_STREAM,
                apkURI
            )
            startActivity(Intent.createChooser(intentShareFile, "Share File"));
        }
        return super.onOptionsItemSelected(item)
    }
}