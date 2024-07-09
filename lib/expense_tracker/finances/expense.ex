defmodule ExpenseTracker.Finances.Expense do
  use Ecto.Schema
  import Ecto.Changeset

  schema "expenses" do
    field :date, :date
    field :description, :string
    field :category, :string
    field :amount, :decimal

    timestamps(type: :utc_datetime)
  end

  @doc false
  def changeset(expense, attrs) do
    expense
    |> cast(attrs, [:date, :amount, :category, :description])
    |> validate_required([:date, :amount, :category, :description])
  end
end
