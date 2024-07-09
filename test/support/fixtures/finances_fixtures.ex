defmodule ExpenseTracker.FinancesFixtures do
  @moduledoc """
  This module defines test helpers for creating
  entities via the `ExpenseTracker.Finances` context.
  """

  @doc """
  Generate a expense.
  """
  def expense_fixture(attrs \\ %{}) do
    {:ok, expense} =
      attrs
      |> Enum.into(%{
        amount: "120.5",
        category: "some category",
        date: ~D[2024-07-08],
        description: "some description"
      })
      |> ExpenseTracker.Finances.create_expense()

    expense
  end
end
