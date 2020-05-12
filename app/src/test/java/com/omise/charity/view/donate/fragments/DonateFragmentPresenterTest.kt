package com.omise.charity.view.donate.fragments

import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.omise.charity.model.Donate
import com.omise.charity.model.DonateForm
import com.omise.charity.model.DonateResult
import com.omise.charity.network.OmiseRepository
import com.omise.charity.util.rx.SchedulersCoupler
import com.omise.charity.view.donate.fragments.helper.DonateErrorMessage
import com.omise.charity.view.donate.fragments.helper.DonateUIValidator
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

internal class DonateFragmentPresenterTest {
    private val view: DonateFragmentView = Mockito.mock(DonateFragmentView::class.java)
    private val scheduler: SchedulersCoupler = Mockito.mock(SchedulersCoupler::class.java)
    private val api: OmiseRepository = Mockito.mock(OmiseRepository::class.java)
    private val uiValidator: DonateUIValidator = Mockito.mock(DonateUIValidator::class.java)
    private val uiErrorMessage: DonateErrorMessage = Mockito.mock(DonateErrorMessage::class.java)
    private val objectUnderTest =
        DonateFragmentPresenter(view, uiValidator, uiErrorMessage, scheduler, api)

    @Before
    fun setUp() {
        removeScheduler()
    }

    @Test
    fun `donate attempt, invalid amount, no api call`() {
        // given
        val donateForm = DonateForm("Test", "token", 0)
        given(uiErrorMessage.invalidAmount()).willReturn("Invalid Amount!")

        // when
        objectUnderTest.donate(donateForm)
        // then
        val ordered = Mockito.inOrder(view)
        ordered.verify(view).clearErrors()
        ordered.verify(view).disableAllInput()
        ordered.verify(view).showProgress()
        ordered.verify(view).hideProgress()
        ordered.verify(view).enableAllInput()
        ordered.verify(view).invalidAmount(uiErrorMessage.invalidAmount())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `donate attempt, valid amount, donate api success, status code 200`() {
        // given
        val donateForm = DonateForm("Test", "token", 10)
        val donateResult = Donate(200, true, "success", "success")
        given(uiValidator.isAmountValid(donateForm.amount)).willReturn(true)
        given(api.donate(donateForm)).willReturn(Single.just(donateResult))


        // when
        objectUnderTest.donate(donateForm)
        // then
        val ordered = Mockito.inOrder(view)
        ordered.verify(view).clearErrors()
        ordered.verify(view).disableAllInput()
        ordered.verify(view).showProgress()
        ordered.verify(view).hideProgress()
        ordered.verify(view).enableAllInput()
        ordered.verify(view).onDonateSuccess()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `donate attempt, valid amount, donate api success, status code 201`() {
        // given
        val donateForm = DonateForm("Test", "token", 100)
        val donateResult = Donate(201, true, "error", "insufficient funds")
        given(uiValidator.isAmountValid(donateForm.amount)).willReturn(true)
        given(api.donate(donateForm)).willReturn(Single.just(donateResult))


        // when
        objectUnderTest.donate(donateForm)
        // then
        val ordered = Mockito.inOrder(view)
        ordered.verify(view).clearErrors()
        ordered.verify(view).disableAllInput()
        ordered.verify(view).showProgress()
        ordered.verify(view).hideProgress()
        ordered.verify(view).enableAllInput()
        ordered.verify(view).onDonateFailure(donateResult.errorMessage)
        verifyNoMoreInteractions(view)
    }

    private fun removeScheduler() {
        given(scheduler.convertToAsyncSingle<DonateResult>())
            .willReturn(SingleTransformer { it })
    }
}