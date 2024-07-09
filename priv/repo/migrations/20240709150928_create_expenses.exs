defmodule ExpenseTracker.Repo.Migrations.CreateExpenses do
  use Ecto.Migration

  def change do
    create table(:expenses) do
      add :date, :date
      add :amount, :decimal
      add :category, :string
      add :description, :text

      timestamps(type: :utc_datetime)
    end
  end
end
